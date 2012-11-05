package spec

import spock.lang.Specification
import foo.SpockRunner

/**
 * Created with IntelliJ IDEA.
 * User: ebiester
 * Date: 10/16/12
 * Time: 7:08 PM
 * To change this template use File | Settings | File Templates.
 */
class TreeDiscoverySpec extends Specification{

        def "Test simple directory discovery"() {
            given:
            def sourceFolder = new File("src/groovy")
            def rootSpecPackage = "spec"
            ClassLoader loader = ClassLoader.getSystemClassLoader()

            List result


            when:
            result = new SpockRunner().findClasses(loader, sourceFolder, rootSpecPackage)

            then:
            result.size() == 4
        }

    def "Test simple spec list of strings"() {
        given:
            def testListOfStrings = ["spec.a.ErrorTestSpec", "spec.a.ABTestSpec", "spec.b.BATestSpec", "spec.AdditionSpec"]

        when:
            def listOfStrings = new SpockRunner().specsAsListOfStrings()

        then:
            listOfStrings == testListOfStrings
    }

}
