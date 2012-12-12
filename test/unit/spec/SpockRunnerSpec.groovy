package spec

import spock.lang.Specification
import com.ebiester.webtestrunner.SpockRunner

import com.ebiester.webtestrunner.bean.ClassMethodPair

/**
 * Created with IntelliJ IDEA.
 * User: ebiester
 * Date: 11/5/12
 * Time: 12:02 PM
 * To change this template use File | Settings | File Templates.
 */
class SpockRunnerSpec extends Specification {

    def "Exec runs a single test"() {
        given:
        List<ClassMethodPair> testList =
            [new ClassMethodPair("spec.a.ErrorTestSpec", "Test results in failure")]

        when:
        def resultList = new SpockRunner().exec(testList)

        then:
        resultList.size() == 1
    }

    def "Exec runs a single test and returns multiple results"() {
        given:
        List<ClassMethodPair> testList =
            [new ClassMethodPair("spec.a.ErrorTestSpec", "Test results in failure"),
             new ClassMethodPair("spec.a.ErrorTestSpec", "Test results in Exception")]

        when:
        def resultList = new SpockRunner().exec(testList)

        then:
        println resultList
        resultList.size() == 2
    }

}
