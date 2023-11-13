package com.example.BehaviourTests;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;


import com.example.TestCase;

public class AttributeTypeTest extends TypeTest {

    private String className;
    private String methodName;
    private Field field;
    private Object expectedValue;
    private Object actualValue;
    
    public AttributeTypeTest(int allocatedMarks, String className, String methodName, Object expectedValue){
        super(allocatedMarks);
        this.methodName = methodName;
        this.className = className;  
        this.expectedValue = expectedValue;
    }

    public String test(){
        actualValue = getType();
        // System.out.println(actualValue);
        // System.out.println(expectedValue);
        try{
            assertEquals(actualValue, expectedValue);
            return "Attribute Type Test Passed !!";
        }
        catch(AssertionError e){
            return "Attribute Type Test Failed !!";
        }
    }
       
    

    public Object getType(){
        field = findAttributeInstance(className, methodName, allClasses);
        return field.getType();
    }


     public static void main (String[] args){
     

        TestCase t = new AttributeTypeTest(1, "AC", "coolBy", int.class );
        String r = t.test();
        System.out.println(r);
    }
}