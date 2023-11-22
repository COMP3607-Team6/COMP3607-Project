package com.example;

import java.util.ArrayList;


/**
 * Manages testcases array
*/
public class TestCaseManager {
    private static ArrayList<TestCase> testCases = new ArrayList<>();

    public static ArrayList<TestCase> getTestCases() {
        return testCases;
    }

    public static void addTestCase(TestCase testCase) {
        testCases.add(testCase);
    }

    public static void removeTestCase(TestCase testCase) {
        testCases.remove(testCase);
    }

    public static void removeTestCases(ArrayList<TestCase> testsToRemove) {
        testCases.removeAll(testsToRemove);
    }

    public static void removeAllTestCases() {
        testCases.clear();
    }

    // public static void sortTestCases() {

    // }
}
