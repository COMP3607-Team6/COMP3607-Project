package com.example.BehaviourTests;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;


import com.example.TestCase;

public class MethodTypeTest extends TypeTest {

    private String className;
    private String methodName;
    private Method method;
    private Object expectedValue;
    private Object actualValue;
    
    public MethodTypeTest(int allocatedMarks, String className, String methodName, Object expectedValue){
        super(allocatedMarks);
        this.methodName = methodName;
        this.className = className;  
        this.expectedValue = expectedValue;
    }

    public String test(){
        try{
            actualValue = getType();
            assertEquals(actualValue, expectedValue);
            testMarks.setTestComment("Method " + methodName + "is of the correct type" + actualValue + ".");
            return "Method " + methodName + " is of the correct return type "+ actualValue + ".";
     
        }
        catch(AssertionError e){
            testMarks.setTestComment("Method " + methodName + " was expected to be of returntype " + expectedValue + " but was of return type " + actualValue);
            return "Method " + methodName + " was expected to be of return type " + expectedValue + " but was of return type " + actualValue;
    
        }
        catch(Exception e){
            testMarks.setTestComment("Method " + methodName + " does not exist in class "+ className + ".");
            return "Method " + methodName + " does not exist in class "+ className + ".";
        }
    }
       
    
    //Check NoMethodException for this method.
    public Object getType(){
        method = findMethodInstance(className, methodName, allClasses);
        return method.getReturnType();
    }


     public static void main (String[] args){
     

        TestCase t = new MethodTypeTest(1, "AC", "coolsBy", int.class );
        String r = t.test();
        System.out.println(r);
    }
}


