package com.example;

import static org.junit.Assert.assertTrue;

public class MethodNameTest extends NamingConventionTest {

    private Object methodObject;
    private String className;
    private String methodName;

    public MethodNameTest(String className, String methodName){
        super();
        this.className = className;
        this.methodName = methodName;
        

    }

    public void test(){
        methodObject = findMethodInstance(className, methodName);
        assertTrue(methodObject != null);

    }

    public static void main (String[] args){
        MethodNameTest m  = new MethodNameTest("CeilingFan", "toString");
        m.test();
    }
    
}
