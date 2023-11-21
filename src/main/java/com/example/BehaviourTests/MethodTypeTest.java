package com.example.BehaviourTests;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;


/**
 * Concrete Class of TypeTest that handles the declared type test for Methods
*/

public class MethodTypeTest extends TypeTest {

    private String className;
    private String methodName;
    private Method method;
    private Object expectedValue;
    private String expectedValueString;
    private Object actualValue;
    
    /**
     * Concrete Class of TypeTest that handles the declared type test for method
    */
    public MethodTypeTest(int allocatedMarks, String className, String methodName, String expectedValue){
        super(allocatedMarks);
        this.methodName = methodName;
        this.className = className;
        this.expectedValueString = expectedValue;
        this.expectedValue = convertKeywordToObject(expectedValue);
        this.testName = "Method Type Test for " + methodName + " belonging to class: " + className;

    }

    public String test(){

        actualValue = getType();

        if(actualValue.equals(className + " class not found !!")){
            testMarks.setTestComment( className + " class not found !!");
            return className + " class not found !!";
        }

        if(actualValue.equals(methodName + " method not found in " + className + " !!")){
            testMarks.setTestComment( methodName + " method not found in " + className + " !!");
            return methodName + " method not found in " + className + " !!";
        }

        try{
          
            assertEquals(actualValue, expectedValue);
            testMarks.setTestPassed(true);
            testMarks.setTestComment("Method " + methodName + "is of the correct type" + actualValue + ".");
            return "Method " + methodName + " is of the correct return type "+ actualValue + ".";
     
        }
        catch(AssertionError e){
            testMarks.setTestComment("Method " + methodName + " was expected to be of returntype " + expectedValueString + " but was of return type " + actualValue);
            return "Method " + methodName + " was expected to be of return type " + expectedValueString + " but was of return type " + actualValue;
    
        }
        catch(Exception e){
            testMarks.setTestComment("Method " + methodName + " does not exist in class "+ className + ".");
            return "Method " + methodName + " does not exist in class "+ className + ".";
        }
    }
    
    /**
    * Returns a Method object's type based on its name
    */
    public Object getType(){

        Class<?> clazz = findClassInstance(className,allClasses);
        
        if(clazz == null){
            return className + " class not found !!";
        }

        method = findMethodInstance(className, methodName, allClasses);

        if(method == null){
            return  methodName + " method not found in " + className + " !!";
        }
        
        return method.getReturnType();
    }

}


