package foo

import org.junit.runner.notification.RunListener
import org.junit.runner.Description
import org.junit.runner.Result
import org.junit.runner.JUnitCore
import org.codehaus.groovy.grails.commons.ApplicationHolder

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
            println("testStarted2 ${description}")
        }

        void testRunFinished(Result result) {
            println("TestFinished ${result.wasSuccessful()}")
            thisResult += result.wasSuccessful() + result.failureCount
        }

    }

    String thisResult = ""

    String exec() {
        println "in run"
        JUnitCore runner = new JUnitCore()
        runner.addListener(new JUnitListener())
        ClassLoader loader = ApplicationHolder.getApplication().getClassLoader()
        def classPath = "spec.AdditionSpec"
        Class clazz = Class.forName(classPath, true, loader)
        try {
            runner.run(clazz)
        } catch (Throwable e) {
            e.printStackTrace()
            println("We crasheded")
        }

        return thisResult
    }

}