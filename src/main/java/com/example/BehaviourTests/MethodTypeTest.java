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
        actualValue = getType();
        // System.out.println(actualValue);
        // System.out.println(expectedValue);
        try{
            assertEquals(actualValue, expectedValue);
            return "Method Type Test Passed !!";
        }
        catch(AssertionError e){
            return "Method Type Test Failed !!";
        }
    }
       
    

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


