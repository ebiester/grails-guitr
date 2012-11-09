import grails.converters.deep.JSON
        import org.junit.runner.JUnitCore
        import org.junit.runner.notification.RunListener
        import org.junit.runner.Description
        import org.junit.runner.Result
        import org.codehaus.groovy.grails.commons.ApplicationHolder
        import foo.SpockRunner

/**
 * Created with IntelliJ IDEA.
 * User: ebiester
 * Date: 10/1/12
 * Time: 12:37 AM
 * To change this template use File | Settings | File Templates.
 *
 * [
 [
 name: "com.ebiester.BasicTest",
 checked: false,
 tests: [[name: "testWillSucceed",  checked: false],
 [name: "testWillFail", checked: false],
 [name: "testWillError", checked: false]
 ]
 ],
 [
 name: "com.ebiester.AdvancedTest",
 checked: false,
 tests: [[name: "funTest1",  checked: false],
 [name: "boringTest2", checked: false]
 ]
 ]
 ];
 */
class RunnerController {
    def testList = {
        def specs = new SpockRunner().specsAsListOfStrings().collect {[name:it, checked: false]}
        println "$specs"
        render new JSON(target: specs)
    }

    def run = {
        println "In Run!S"
        def eaches = params.tests.each {print "test: ${it}"}
        println "tests: $eaches"

        Result result = new SpockRunner().exec(["spec.a.ErrorTestSpec"]);
        //if no result, exception thrown

        /*
        def resultJSON = [
                result.failureCount
                result.runCount
                result.getFailures()

        ] */

        render "" //exec
    }


    def runner = {
    }
}

