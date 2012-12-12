package com.ebiester.webtestrunner.bean;

import org.codehaus.groovy.grails.commons.ApplicationHolder;

public class ClassMethodPair {
    private Class clazz;
    private String methodName;

    public ClassMethodPair(String className, String methodName) {
        setMethodName(methodName);
        setClazz(className);
    }

    public ClassMethodPair(Class clazz, String methodName) {
        setMethodName(methodName);
        setClazz(clazz);
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public void setClazz(String className) {
        //Note: If we're running our own unit tests, the Grails application loader won't exist.
        //Currently grails only code. Okay for current purposes.
        ClassLoader loader = null;

        if (ApplicationHolder.getApplication() != null &&
                ApplicationHolder.getApplication().getClassLoader() != null) {
            loader = ApplicationHolder.getApplication().getClassLoader();
        } else {
            loader = ClassLoader.getSystemClassLoader();
        }
        try {
            this.clazz = Class.forName(className, true, loader);
        } catch (ClassNotFoundException cfne) {
            //TODO: Have to think about this.
            cfne.printStackTrace();
        }
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}