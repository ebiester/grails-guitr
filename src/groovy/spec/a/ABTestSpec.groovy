package spec.a

import spock.lang.Specification

/**
 * Created with IntelliJ IDEA.
 * User: ebiester
 * Date: 10/16/12
 * Time: 7:10 PM
 * To change this template use File | Settings | File Templates.
 */
class ABTestSpec extends Specification {
    def "Successful Test"() {
        given:
        def a = 1
        def b = 0

        when:
        def c = 1

        then:
        (a + b) == c
    }
}
