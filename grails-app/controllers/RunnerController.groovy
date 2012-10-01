import grails.converters.deep.JSON

/**
 * Created with IntelliJ IDEA.
 * User: ebiester
 * Date: 10/1/12
 * Time: 12:37 AM
 * To change this template use File | Settings | File Templates.
 *
 * Start 12:20 am
 * End 3:20am (2 hours)
 *
 * Start 1:00 pm
 * End 3:00 pm
 */
class RunnerController {
    def testList = {
        def tests = [
                [
                        name: "com.ebiester.basicTest",
                        tests: ["testWillSucceed",
                                "testWillFail",
                                "testWillError"
                        ]
                ],
                [
                        name: "com.ebiester.advancedTest",
                        tests: ["funTest1",
                                "boringTest2"
                        ]
                ]
        ];

        render new JSON(target: tests)
    }


    def runner = {}
}
