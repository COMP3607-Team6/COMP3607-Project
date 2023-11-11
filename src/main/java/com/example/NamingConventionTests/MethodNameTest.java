package com.example.NamingConventionTests;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;

public class MethodNameTest extends NamingConventionTest {

    private Method methodObject;
    private String className;
    private String methodName;
    // private Marks testMarks;

    public MethodNameTest(String className, String methodName, int marksAllocated){
        super(marksAllocated);
        this.className = className;
        this.methodName = methodName;
       // this.testMarks = new Marks(marksAllocated);
    }

    public String test(){
        try{
            System.out.println(toString());
            methodObject = findMethodInstance(className, methodName, allClasses);
            assertTrue(methodObject != null);
            testMarks.setTestPassed(true);
            System.out.println(toString());
            return "Method: " + methodName + " Found";
        }
        catch(AssertionError e){
            return "Method: " + methodName + " Not Found!";
        }

    }

    @Override
    public String toString() {
            return "{" +
                " methodName='" + methodName + "'" +
                ", className='" +  className + "'" +
                ", marksObject='" +  testMarks.toString() + "'" +
                ", marks='" +  testMarks.getTestMarks() + "'" +
                ", Test pass='" +  testMarks.getTestPassed() + "'" +
                "}";
    }

    public static void main (String[] args){
        MethodNameTest m  = new MethodNameTest("CeilingFan", "toString",1);
        System.out.println(m.test());
    }
    
}
