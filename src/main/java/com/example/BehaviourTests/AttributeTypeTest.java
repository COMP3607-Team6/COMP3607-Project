package com.example.BehaviourTests;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import com.example.TestCase;


public class AttributeTypeTest extends TypeTest {

    private String className;
    private String attributeName;
    private Field field;
    private Object expectedValue;
    private Object actualValue;
    
    public AttributeTypeTest(int allocatedMarks, String className, String attributeName, Object expectedValue){
        super(allocatedMarks);
        this.attributeName = attributeName;
        this.className = className;  
        this.expectedValue = expectedValue;
        this.testName = "Attribute Type Test for " + attributeName + " belonging to class: " + className;
    }

    public String test(){
        try{
            actualValue = getType();
            assertEquals(actualValue, expectedValue);
            testMarks.setTestPassed(true);
            
            testMarks.setTestComment("Attribute " + attributeName + "is of the correct type" + actualValue + ".");
            return "Attribute " + attributeName + " is of the correct type "+ actualValue + ".";
        }
        catch(AssertionError e){
            testMarks.setTestComment("Attribute " + attributeName + " was expected to be of type " + expectedValue + " but was of type " + actualValue);
            return "Attribute " + attributeName + " was expected to be of type " + expectedValue + " but was of type " + actualValue;
        }
        catch(Exception e){
            testMarks.setTestComment("Attribute " + attributeName + " does not exist in class "+ className + ".");
            return "Attribute " + attributeName + " does not exist in class "+ className + ".";
        }
    }
       
    

    public Object getType(){
        field = findAttributeInstance(className, attributeName, allClasses);
        return field.getType();
    }


     public static void main (String[] args){
     

        TestCase t = new AttributeTypeTest(1, "AC", "coolBy", int.class );
        String r = t.test();
        System.out.println(r);
    }
}