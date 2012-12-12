package spec

import spock.lang.Specification

import com.ebiester.webtestrunner.MethodClassRetriever
import com.ebiester.webtestrunner.bean.ClassSpecsPair

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
            def sourceFolder = new File("src/groovy/spec")
            def rootSpecPackage = "spec"
            ClassLoader loader = ClassLoader.getSystemClassLoader()

            List result


            when:
            result = new MethodClassRetriever().findClasses(loader, sourceFolder, rootSpecPackage)

            then:
            result.size() == 4
        }

    def "Test simple spec list of strings"() {
        given:
            def sourceFolder = new File("src/groovy/spec")
            def rootSpecPackage = "spec"
            ClassLoader loader = ClassLoader.getSystemClassLoader()
            MethodClassRetriever methodClassRetriever = new MethodClassRetriever()
            List<Class> classes =
                methodClassRetriever.findClasses(loader, sourceFolder, rootSpecPackage)

        when:
            List<ClassSpecsPair> classSpecsPairList =
                methodClassRetriever.specsAsClassSpecsPairList(classes)

        then:
           classSpecsPairList.size() == 4
    }

}
