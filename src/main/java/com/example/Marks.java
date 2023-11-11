package com.example;

public class Marks {
    
    private int testMarks;
    private boolean testPassed;

    public Marks(int marks){
        this.testMarks = marks;
        this.testPassed = false;
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

}
