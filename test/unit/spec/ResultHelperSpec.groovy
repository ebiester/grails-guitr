package spec

import foo.SpockRunner
import foo.ResultHelper
import spock.lang.Specification

/**
 * Created with IntelliJ IDEA.
 * User: ebiester
 * Date: 11/5/12
 * Time: 1:31 PM
 * To change this template use File | Settings | File Templates.
 */
class ResultHelperSpec extends Specification {
    def "isTestFailure catches failures"() {

        given:
        def testListOfStrings = ["spec.a.ErrorTestSpec"]

        when:
        def result = new SpockRunner().exec(testListOfStrings)

        then:
        ResultHelper.getTestFailureCount(result) == 1
    }

    def "isTestError catches errors"() {

        given:
        def testListOfStrings = ["spec.a.ErrorTestSpec"]

        when:
        def result = new SpockRunner().exec(testListOfStrings)

        then:
        ResultHelper.getTestErrorCount(result) == 1
    }

    def "IsTestFailure and isTestError account for all errors"() {

        given:
        def testListOfStrings = ["spec.a.ErrorTestSpec"]

        when:
        def result = new SpockRunner().exec(testListOfStrings)

        then:
        ResultHelper.getTestFailureCount(result) + ResultHelper.getTestErrorCount(result) == result.failureCount
    }

}
