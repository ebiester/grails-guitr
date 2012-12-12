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

        Result result = resultList.get(0)

        def resultObject = [
                runCount: result.runCount,
                //errorCount: result.errorCount,
                failureCount: result.failureCount,
                failures: result.getFailures(),
                runtime: result.runTime
        ]

        render new JSON(resultObject) //exec
    }


    def runner = {
    }
}

