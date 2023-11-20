package com.example.AssignmentSpecificationPortal;

import java.util.ArrayList;

public class TestInfo {
    private ArrayList<String> testNames;
    private ArrayList<String> testDescriptions;

    public TestInfo(ArrayList<String> testNames, ArrayList<String> testDescriptions) {
        this.testNames = testNames;
        this.testDescriptions = testDescriptions;
    }

    public TestInfo() {
        this.testNames = new ArrayList<String>();
        this.testDescriptions = new ArrayList<String>();
    }

    public void addTest(String name, String description) {
        testNames.add(name);
        testDescriptions.add(description);
    }

    public void addTests(TestInfo tests) {
        // System.out.println("add tests method before: ");
        // System.out.println(testNames);
        // System.out.println(testDescriptions);
        testNames.addAll(tests.getTestNames());
        testDescriptions.addAll(tests.getTestDescriptions());
        // System.out.println("add tests method after: ");
        // System.out.println(testNames);
        // System.out.println(testDescriptions);
    }

    public ArrayList<String> getTestNames() {
        return testNames;
    }

    public ArrayList<String> getTestDescriptions() {
        return testDescriptions;
    }

    public String getTestName(int i) {
        return testNames.get(i);
    }

    public String getTestDescription(int i) {
        return testDescriptions.get(i);
    }

    public int getSize() {
        return testNames.size();
    }

    public void clearTests() {
        testNames.clear();
        testDescriptions.clear();
    }

    public void print() {
        System.out.println("TEST INFO toString(): ");
        System.out.println(testNames);
        // System.out.println(testDescriptions);
    }
}
