package com.ebiester.webtestrunner;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.List;

public class JUnitResultHelper {
    private static Boolean isTestFailure(Failure failure) {
        return AssertionError.class.isAssignableFrom(failure.getException().getClass());
    }

    public static Integer getTestFailureCount(Result result) {
        Integer failureCount = 0;
        for (Failure failure : result.getFailures()) {
            if (isTestFailure(failure)) {
                failureCount++;
            }
        }
        return failureCount;
    }

    public static Integer getTestFailureCount(List<Result> results) {
        Integer failureCount = 0;
        for (Result result : results) {
            failureCount += getTestFailureCount(result);
        }

        return failureCount;
    }

    public static Integer getTestErrorCount(Result result) {
        //TODO: Refactor. Almost exact code as above.
        Integer errorCount = 0;
        for (Failure failure : result.getFailures()) {
            if (!isTestFailure(failure)) {
                errorCount++;
            }
        }
        return errorCount;
    }

    public static Integer getTestErrorCount(List<Result> results) {
        //TODO: Refactor. Almost exact code as above.
        Integer errorCount = 0;
        for (Result result : results) {
            errorCount += getTestErrorCount(result);
        }

        return errorCount;
    }
}
