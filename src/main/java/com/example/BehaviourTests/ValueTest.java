package com.example.BehaviourTests;


/**
 * Abstract Class of Behaviour Test which handles tests relating to testing the type and value of attribute.
*/
public abstract class ValueTest extends BehaviourTest{
    

    public ValueTest(int allocatedMarks){
        super(allocatedMarks);
    }

    public abstract Object getValue();

}
