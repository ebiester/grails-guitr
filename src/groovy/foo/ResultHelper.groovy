package foo

import org.junit.runner.notification.Failure
import org.junit.runner.Result

/**
 * Created with IntelliJ IDEA.
 * User: ebiester
 * Date: 11/5/12
 * Time: 1:27 PM
 * To change this template use File | Settings | File Templates.
 */
class ResultHelper {
    private static Boolean isTestFailure(Failure failure) {
        AssertionError.class.isAssignableFrom(failure.exception.getClass())
    }

    static Integer getTestFailureCount(Result result) {
        result.failures.findAll {isTestFailure(it)}.size()
    }

    static Integer getTestErrorCount(Result result) {
        result.failures.findAll {!isTestFailure(it)}.size()
    }
}
