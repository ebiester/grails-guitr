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
 *
 */
class RunnerController {
    def testList = {
        def specs = new SpockRunner().specsAsListOfStrings().collect {[name:it, checked: false]}
        println "$specs"
        render new JSON(target: specs)
    }

    def run = {
        println "In Run!S"
        List<String> tests = params?.tests?.split(',')

        //TODO: validation here.
        Result result = new SpockRunner().exec(tests);
        //if no result, exception thrown


        def resultObject = [
                runCount: result.runCount,
                failureCount: result.failureCount,
                runCount: result.runCount,
                failures: result.getFailures(),
                runtime: result.runTime
        ]

        println resultObject

        render new JSON(target: resultObject) //exec
    }


    def runner = {
    }
}

