package spec.a

import spock.lang.Specification

/**
 * Created with IntelliJ IDEA.
 * User: ebiester
 * Date: 10/16/12
 * Time: 7:10 PM
 * To change this template use File | Settings | File Templates.
 */
class ErrorTestSpec extends Specification {
    def "Test results in failure"() {
        given:
        def a = 1
        def b = 2

        when:
        def c = 3

        then:
        a == b
    }

    def "Test results in Exception"() {
        given:
        def a = 1
        def b = 0

        when:
        def c = 3

        then:
        (a / b) == 12345
    }

}
