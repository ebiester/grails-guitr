import org.junit.runner.Result
import com.ebiester.webtestrunner.SpockRunner
import com.ebiester.webtestrunner.MethodClassRetriever
import grails.converters.*
import com.ebiester.webtestrunner.bean.ClassSpecsPair
import org.codehaus.groovy.grails.commons.ApplicationHolder
import com.ebiester.webtestrunner.bean.ClassMethodPair
import org.codehaus.groovy.grails.web.json.JSONArray
import org.codehaus.groovy.grails.web.json.JSONObject

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
        String directoryName = params?.baseDirectory
        String packageName = params?.packageName

        //TODO: error checking
        //c:/Users/ebiester/work/WebTestRunner/src/groovy/spec
        //TODO: replace \ with /
        //TODO: no specs shows as "no results found"

        ClassLoader loader = ApplicationHolder.getApplication().getClassLoader()
        MethodClassRetriever methodClassRetriever = new MethodClassRetriever()
        File baseDirectory = new File(directoryName)
        List<Class> classes = methodClassRetriever.findClasses(loader,
                baseDirectory, packageName)
        List<ClassSpecsPair> specs = methodClassRetriever.specsAsClassSpecsPairList(classes)

        //if exception or null, "No classes found. Please check your directory and package"?
        render specs as JSON
    }

    def run = {
        JSONArray tests = JSON.parse(params?.tests)
        println tests

        List<ClassMethodPair> testList = []
//            [new ClassMethodPair("spec.a.ErrorTestSpec", "Test results in failure"),
//                    new ClassMethodPair("spec.a.ErrorTestSpec", "Test results in Exception")]

        tests.each { JSONObject test ->
            String className = test.get("className")
            JSONObject jsonSpec = test.get("spec")
            String specName = jsonSpec.get("spec")
            testList.add(new ClassMethodPair(className, specName))
        }

        //TODO: validation here.
        List<Result> resultList = new SpockRunner().exec(testList);
        //if no result, exception thrown


        //really statistics
        def resultObject = [
                runCount: 0,
                //errorCount: result.errorCount,
                failureCount: 0,
                failures: "",
                runtime: 0
        ]

        resultList.each { result ->
            resultObject.runCount += result.runCount
            //resultObject.errorCount += result.errorCount,
            resultObject.failureCount += result.failureCount
            //resultObject.failures += result.getFailures()
                    //runtime: result.runTime
        }

        //get list of specs that will update on the client

        render new JSON(resultObject) //exec
    }


    def runner = {
        //TODO: pull default directory from properties
        //TODO: Save favorites, or some sort of preset run
    }
}

