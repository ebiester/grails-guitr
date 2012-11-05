package spec

import spock.lang.Specification
import foo.SpockRunner
import foo.ResultHelper

/**
 * Created with IntelliJ IDEA.
 * User: ebiester
 * Date: 11/5/12
 * Time: 12:02 PM
 * To change this template use File | Settings | File Templates.
 */
class JunitRunnerInvocationSpec extends Specification {

    def "Exec catches both errors and failures"() {

        given:
        def testListOfStrings = ["spec.a.ErrorTestSpec"]

        when:
        def result = new SpockRunner().exec(testListOfStrings)

        then:
        result.failureCount == 2
    }
}
