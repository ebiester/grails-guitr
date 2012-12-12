package spec

import spock.lang.Specification
import com.ebiester.webtestrunner.MethodClassRetriever
import com.ebiester.webtestrunner.bean.ClassSpecsPair

/**
 * Created with IntelliJ IDEA.
 * User: ebiester
 * Date: 11/15/12
 * Time: 1:17 PM
 * To change this template use File | Settings | File Templates.
 */
class MethodClassRetrieverSpec extends Specification {

    def "getFeatureMethodNames returns method names for a class"() {
        given:
        ClassLoader loader = ClassLoader.getSystemClassLoader()
        Class clazz = Class.forName("spec.a.ErrorTestSpec", true, loader)

        when:
        List<String> featureMethodNames =
            new MethodClassRetriever().getFeatureMethodNames(clazz)

        then:
        featureMethodNames.size() == 2
    }

    def "specsAsClassSpecsPairList returns all specs"() {
        given:
        MethodClassRetriever mcr = new MethodClassRetriever()
        List<ClassSpecsPair> specs = null
        List<Class> classList = [spec.a.ABTestSpec, spec.a.ErrorTestSpec]

        when:
        specs = mcr.specsAsClassSpecsPairList(classList)

        then:
        specs.each {
            println it.className
            println it.class
            it.specs.each {
                println it.class
                //println it.isChecked
                //println it.spec
                println it
            }
        }

        specs.size() == 2
        ClassSpecsPair firstClassSpecsPair = specs.get(0);
        firstClassSpecsPair.specs.size() == 1
        ClassSpecsPair secondClassSpecsPair = specs.get(1);
        secondClassSpecsPair.specs.size() == 2
    }
}
