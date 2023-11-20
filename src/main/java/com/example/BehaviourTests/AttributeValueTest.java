package com.example.BehaviourTests;

import static org.junit.Assert.assertEquals;
import java.lang.reflect.Field;

import com.example.TestCase;

public class AttributeValueTest extends ValueTest {
    
    private Object actualValue;
    private String attributeName;
    private String className;
   

    public AttributeValueTest(String attributeName, String className, int allocatedMarks, Object value) {
        super(allocatedMarks);
        this.attributeName = attributeName;
        this.className = className;
        this.actualValue = value;
        this.testName = "Attribute Value Test for " + attributeName + " belonging to class: " + className;

       
    }

    public String test(){
        Object value = getValue();

        //System.out.println(value);


        if(value.equals(className + " class not found !!")){
             System.out.println(value);
            return className + " class not found !!";
        }


        else if(value.equals(attributeName + " not found in " + className + "!!")){
           return attributeName + " not found in " + className + " !!";
        }
      
        else{
        
            try{
                assertEquals("Attribute Value Test", value, actualValue);
                testMarks.setTestPassed(true);
                testMarks.setTestComment("Attribute " + attributeName + " is of the correct value "+ actualValue + ".");
                return "Attribute " + attributeName + " is of the correct value  "+ actualValue + ".";
            }
            catch(AssertionError e){
                testMarks.setTestComment("Attribute " + attributeName + " was expected to be " + value + " but was " + actualValue);
                return "Attribute " + attributeName + " was expected to be " + value + " but was " + actualValue;
            }
        }
    }


    public Object getValue(){

        Class<?> clazz = findClassInstance(className,allClasses);
      
        
        if(clazz == null){
            return className + " class not found !!";
        }


        Field field = findAttributeInstance(className, attributeName, allClasses); 

        if(field == null){
            return attributeName + " not found in " + className + "!!";
        }

    try {

        field.setAccessible(true);
       

        Object value = field.get(oldFindClassInstance(className));

     
        return value;

    } catch (IllegalAccessException e) {
        e.printStackTrace();
        System.out.println("Cannot access the field");
    } catch (IllegalArgumentException e) {
        e.printStackTrace();
        System.out.println("IllegalArgumentException: " + e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("An unexpected error occurred: " + e.getMessage());
    }

    return null; // Return an empty string if there's any issue
}


    public static void main (String[] args){
        TestCase t = new AttributeValueTest("coolBy", "AC",1, 5);
        String r = t.test();
        System.out.println(r);
    }

}
