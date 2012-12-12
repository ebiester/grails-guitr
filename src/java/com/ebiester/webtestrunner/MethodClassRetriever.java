package com.ebiester.webtestrunner;

import com.ebiester.webtestrunner.bean.ClassSpecsPair;
import com.ebiester.webtestrunner.bean.Spec;
import org.spockframework.runtime.SpecInfoBuilder;
import org.spockframework.runtime.model.FeatureInfo;
import org.spockframework.runtime.model.SpecInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MethodClassRetriever {

/**
 * Recursive method used to find all classes in a given directory tree
 *
 * @param sourceFolder   The base directory
 * @param packageName The package name for classes found inside the base directory
 * @return a list of groovy classes within the directory
 */
    public List<Class> findClasses(ClassLoader loader, File sourceFolder, String packageName) {
        List<Class> classes = new ArrayList<Class>();
        if (!sourceFolder.isDirectory()) {
            throw new IllegalArgumentException("Specified directory does not exist.");
        }

        File[] files = sourceFolder.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                //.git, etc
                if (!file.getName().contains(".")) {
                    String subPackage = packageName + "." + file.getName();
                    classes.addAll(findClasses(loader, file, subPackage));
                }
            } else if (file.getName().endsWith(".groovy")) {
                try {
                    classes.add(Class.forName(packageName + '.' +
                        file.getName().substring(0, file.getName().length() - 7), true, loader));
                } catch (ClassNotFoundException cnfe) {
                    //Not sure what to do yet. If the class doesn't exist, perhaps it is a broken class?
                    //Are we in the wrong classloader? Is it a groovy script with no class?
                    // Throwing may not be the right solution. TODO: Think about this
                    cnfe.printStackTrace();
                }
            }
        }

        return classes;
    }

    public List<ClassSpecsPair> specsAsClassSpecsPairList(List<Class> classes) {
        List<ClassSpecsPair> returnList = new ArrayList<ClassSpecsPair>();
        for (Class clazz : classes) {
            List<String> methodNames = getFeatureMethodNames(clazz);
            ClassSpecsPair csp =
                new ClassSpecsPair(clazz.getName(),
                        getSpecsFromMethodNameList(methodNames));
            returnList.add(csp);
        }

        return returnList;
    }

    private List<Spec> getSpecsFromMethodNameList(List<String> methodNames) {
        ArrayList<Spec> specList = new ArrayList<Spec>();
        for (String methodName : methodNames) {
            specList.add(new Spec(methodName, false));
        }

        return specList;
    }

    private List<String> getFeatureMethodNames(Class clazz) {
        SpecInfoBuilder builder = new SpecInfoBuilder(clazz);
        SpecInfo specInfo = builder.build();
        List<String> methodNames = new ArrayList<String>();
        for (FeatureInfo featureInfo :specInfo.getFeatures()) {
            methodNames.add(featureInfo.getFeatureMethod().getName());
        }

        return methodNames;
    }
}
