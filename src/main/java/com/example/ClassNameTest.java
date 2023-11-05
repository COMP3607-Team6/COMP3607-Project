package com.example;

import static org.junit.Assert.assertTrue;

public class ClassNameTest extends NamingConventionTest {
    
    private Object classObject;
    private String className;

    public ClassNameTest(String className){
        super();
        this.className = className;
        
    

    }


    public String test(){

        classObject = findClassInstance(className);
        
        

        try{
            assertTrue("Class Name Test 1", classObject != null);
            return "Class Name: " + className + " Found.";
        }
        catch(AssertionError e){
            return "Class Name:" + className + " not found.";
        }


        // if (classObject == null){
        //     assertTrue(false);
        // }
        // else{
        //     assertTrue(true);
        //     System.out.println(className + " exists!");
        // }


    }

    public static void main (String[] args){
        ClassNameTest c  = new ClassNameTest("CeilingFan");
        String value = c.test();
        System.out.println("   -----------------------------");
        System.out.println(value);
    }

}
