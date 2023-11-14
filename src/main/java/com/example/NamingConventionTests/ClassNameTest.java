package com.example.NamingConventionTests;

import static org.junit.Assert.assertTrue;

public class ClassNameTest extends NamingConventionTest {
    
    private Class<?> classObject;
    private String className;

    public ClassNameTest(String className){
        super();
        this.className = className;

    }


    public String test(){

        classObject = findClassInstance(className,allClasses);
        
        

        try{
            assertTrue("Class Name Test 1", classObject != null);
            return "Class Name: " + className + " Found.";
        }
        catch(AssertionError e){
            return "Class Name:" + className + " not found.";
        }

    }

    public static void main (String[] args){
        ClassNameTest c  = new ClassNameTest("Device");
        String value = c.test();
        System.out.println("   -----------------------------");
        System.out.println(value);
    }

}
