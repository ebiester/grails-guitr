package spec

import com.ebiester.webtestrunner.SpockRunner
import com.ebiester.webtestrunner.ResultHelper
import spock.lang.Specification
import com.ebiester.webtestrunner.bean.ClassMethodPair

/**
 * Created with IntelliJ IDEA.
 * User: ebiester
 * Date: 11/5/12
 * Time: 1:31 PM
 * To change this template use File | Settings | File Templates.
 */
class ResultHelperSpec extends Specification {

    def "isTestFailure catches a failure but not exception"() {
        given:
        List<ClassMethodPair> testList =
            [new ClassMethodPair("spec.a.ErrorTestSpec", "Test results in failure"),
             new ClassMethodPair("spec.a.ErrorTestSpec", "Test results in Exception"),
             new ClassMethodPair("spec.a.ErrorTestSpec", "Test results in failure")]

        when:
        def resultList = new SpockRunner().exec(testList)

        then:
        resultList.size() == 3
        ResultHelper.getTestFailureCount(resultList) == 2
    }

    def "isTestError catches exception but not failure"() {
        given:
        List<ClassMethodPair> testList =
            [new ClassMethodPair("spec.a.ErrorTestSpec", "Test results in Exception"),
             new ClassMethodPair("spec.a.ErrorTestSpec", "Test results in failure"),
             new ClassMethodPair("spec.a.ErrorTestSpec", "Test results in Exception")]

        when:
        def resultList = new SpockRunner().exec(testList)

        then:
        resultList.size() == 3
        ResultHelper.getTestErrorCount(resultList) == 2
    }

    def "IsTestFailure and isTestError account for all errors"() {
        given:
        List<ClassMethodPair> testList =
            [new ClassMethodPair("spec.a.ErrorTestSpec", "Test results in Exception"),
             new ClassMethodPair("spec.a.ErrorTestSpec", "Test results in failure"),
             new ClassMethodPair("spec.a.ErrorTestSpec", "Test results in Exception")]

        when:
        def resultList = new SpockRunner().exec(testList)

        then:
        ResultHelper.getTestFailureCount(resultList) + ResultHelper.getTestErrorCount(resultList) == resultList.size()
    }

}
