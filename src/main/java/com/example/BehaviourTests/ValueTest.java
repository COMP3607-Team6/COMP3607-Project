package com.example.BehaviourTests;

public abstract class ValueTest extends BehaviourTest{
    

    public ValueTest(int allocatedMarks){
        super(allocatedMarks);
    }

    public abstract Object getValue();

}
