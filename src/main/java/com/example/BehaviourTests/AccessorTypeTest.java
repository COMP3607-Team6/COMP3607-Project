package com.example.BehaviourTests;


public abstract class AccessorTypeTest extends BehaviourTest{

    public AccessorTypeTest(int allocatedMarks){
        super(allocatedMarks);
    }

    public abstract int getModifier();
}