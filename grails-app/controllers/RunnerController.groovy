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
 */
class RunnerController {
    def testSuite = [
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
    def testList = {

        render new JSON(target: testSuite)
    }

    def run = {
        String exec = new SpockRunner().exec();
        render exec
    }


    def runner = {
    }
}

