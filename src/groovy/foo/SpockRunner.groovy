package foo

import org.junit.runner.notification.RunListener
import org.junit.runner.Description
import org.junit.runner.Result
import org.junit.runner.JUnitCore
import org.codehaus.groovy.grails.commons.ApplicationHolder
import spec.a.ErrorTestSpec

/**
 * Created with IntelliJ IDEA.
 * User: ebiester
 * Date: 10/9/12
 * Time: 10:28 PM
 * To change this template use File | Settings | File Templates.
 */
class SpockRunner {
    class JUnitListener extends RunListener {
        void testStarted(Description description) {
            println("testStarted ${description}")
        }

        void testRunFinished(Result result) {
            println("TestFinished ${result.wasSuccessful()}")
            //what was thisResult supposed to do?
            //thisResult += "#{result.wasSuccessful()} #{result.failureCount}"
        }

    }


    //Adapted from http://dzone.com/snippets/get-all-classes-within-package

/**
 * Recursive method used to find all classes in a given directory and subdirs.
 *
 * @param directory   The base directory
 * @param packageName The package name for classes found inside the base directory
 * @return The classes
 * @throws ClassNotFoundException
 */
    def List<Class> findClasses(ClassLoader loader, File sourceFolder, String packageName) {
        /*
        //ClassLoader loader = ApplicationHolder.getApplication().getClassLoader()
        List<Class> classes = new ArrayList<Class>()
        String specFolderPath = packageName.replace('.', '/');
        File baseSpecDirectory = new File(sourceFolder, specFolderPath)
        println "baseSpecDirectory = ${baseSpecDirectory.absolutePath}"
        //new File(baseFile, "com/ebiester/fun")
        if (!sourceFolder.exists()) {
            throw new IllegalArgumentException("Specified directory does not exist.")
        }

        File[] files = baseSpecDirectory.listFiles()
        files.each {file ->
            if (file.isDirectory()) {
                //.git, etc
                if (!file.getName().contains(".")) {
                    classes.addAll(findClasses(loader, baseSpecDirectory, subpackage))
                }
            } else if (file.getName().endsWith(".groovy")) {
                classes.add(Class.forName(packageName + '.' +
                    file.getName().substring(0, file.getName().length() - 7)), true, loader)
            }
        }
        return classes;
        */
        [ErrorTestSpec, spec.a.ABTestSpec, spec.b.BATestSpec, spec.AdditionSpec]
    }

    def List<String> specsAsListOfStrings() {

        def classes = [ErrorTestSpec, spec.a.ABTestSpec, spec.b.BATestSpec, spec.AdditionSpec]
        classes.collect {it.getName()}
    }

    String thisResult = ""

    Result exec(List<String> testList) {
        println "in run"
        JUnitCore runner = new JUnitCore()
        runner.addListener(new JUnitListener())
        //If we're running our own unit tests, our own system class loader is sufficient.
        ClassLoader loader = ApplicationHolder?.getApplication()?.getClassLoader() ?: ClassLoader.getSystemClassLoader()
        def firstTest = testList.get(0)
        Class clazz = Class.forName(firstTest, true, loader)
        Result result
        try {
            result = runner.run(clazz)
        } catch (Throwable e) {
            e.printStackTrace()
            println("We crasheded")
            throw e
        }

        return result
    }

}