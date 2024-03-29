package com.example;

/**
 * Class which stores the marks and results of a Test;
*/
public class TestResult {
    
    private int testMarks;
    private boolean testPassed;
    private String testComment;


    public TestResult(int marks){
        this.testMarks = marks;
        this.testPassed = false;
        this.testComment = "";
    }
    
    public String getTestComment() {
        return this.testComment;
    }


    public int getTestMarks() {
        return this.testMarks;
    }

    public void setTestMarks(int testMarks) {
        this.testMarks = testMarks;
    }

    public boolean getTestPassed() {
        return this.testPassed;
    }

    public void setTestPassed(boolean testPassed) {
        this.testPassed = testPassed;
    }

    public void setTestComment(String testComment) {
        this.testComment = testComment;
    }



}
