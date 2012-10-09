package spec

import spock.lang.Specification

/**
 * Created with IntelliJ IDEA.
 * User: ebiester
 * Date: 10/9/12
 * Time: 10:29 PM
 * To change this template use File | Settings | File Templates.
 */
class AdditionSpec extends Specification {

    def "Test simple addition"() {
        given:
        def x = 2
        def y = 1
        def result
        println "given"

        when:
        result = x + y
        println "when"

        then:
        println "then"
        result == 3
        println "after bad result"
    }
}
