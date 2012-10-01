import grails.converters.deep.JSON

/**
 * Created with IntelliJ IDEA.
 * User: ebiester
 * Date: 10/1/12
 * Time: 12:37 AM
 * To change this template use File | Settings | File Templates.
 *
 */
class RunnerController {
    def tests = [
            [
                    name: "com.ebiester.basicTest",
                    checked: false,
                    tests: [[name: "testWillSucceed",  checked: false],
                            [name: "testWillFail", checked: false],
                            [name:"testWillError", checked: false]
                    ]
            ],
            [
                    name: "com.ebiester.advancedTest",
                    checked: false,
                    tests: [[name: "funTest1",  checked: false],
                            [name: "boringTest2", checked: false]
                    ]
            ]
    ];
    def testList = {

        render new JSON(target: tests)
    }


    def runner = {}
}
