package com.ebiester.webtestrunner;

import com.ebiester.webtestrunner.bean.ClassMethodPair;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import java.util.ArrayList;
import java.util.List;

public class SpockRunner {
    class JUnitListener extends RunListener {
        public void testStarted(Description description) {
            System.out.println("testStarted ${description}");
        }

        public void testRunFinished(Result result) {
            System.out.println("TestFinished ${result.wasSuccessful()}");
            //what was thisResult supposed to do?
            //thisResult += "#{result.wasSuccessful()} #{result.failureCount}"
        }

    }

    List<Result> exec(List<ClassMethodPair> testList) {
        //Perhaps better way to handle this?
        if (testList.isEmpty()) {
            return null;
        }

        JUnitCore runner = new JUnitCore();
        runner.addListener(new JUnitListener());
        List<Result> resultList = new ArrayList<Result>();
        for (ClassMethodPair cmp : testList) {
            resultList.add(runner.run(Request.method(cmp.getClazz(), cmp.getMethodName())));
        }

        return resultList;
    }

}