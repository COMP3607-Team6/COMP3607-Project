package com.example.BehaviourTests;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

/**
 * Concrete Class of TypeTest that handles the declared type test for attributes
*/
public class AttributeTypeTest extends TypeTest {

    private String className;
    private String attributeName;
    private Field field;
    private Object expectedValue;
    private String expectedValueString;
    private Object actualValue;
    
    public AttributeTypeTest(int allocatedMarks, String className, String attributeName, String expectedValue){
        super(allocatedMarks);
        this.attributeName = attributeName;
        this.className = className;
        this.expectedValueString = expectedValue;
        this.expectedValue = convertKeywordToObject(expectedValue);
        this.testName = "Attribute Type Test for " + attributeName + " belonging to class: " + className;
    }

    public String test(){
        try{
            if(expectedValue == null){
                throw new IllegalStateException("Attribute" + attributeName + " does not exist");
            }

            actualValue = getType();
            assertEquals(actualValue, expectedValue);
            testMarks.setTestPassed(true);
            
            testMarks.setTestComment("Attribute " + attributeName + "is of the correct type" + expectedValueString + ".");
            return "Attribute " + attributeName + " is of the correct type "+ expectedValueString + ".";
        }
        catch(AssertionError e){
            testMarks.setTestComment("Attribute " + attributeName + " was expected to be of type " + expectedValueString + " but was of type " + actualValue);
            return "Attribute " + attributeName + " was expected to be of type " + expectedValueString + " but was of type " + actualValue;
        }
        catch(Exception e){
            testMarks.setTestComment("Attribute " + attributeName + " does not exist in class "+ className + ".");
            return "Attribute " + attributeName + " does not exist in class "+ className + ".";
        }
    }
       
    
    /**
    * Returns a Field object's type based on its name
    */
    public Object getType(){
        field = findAttributeInstance(className, attributeName, allClasses);
        return field.getType();
    }

}