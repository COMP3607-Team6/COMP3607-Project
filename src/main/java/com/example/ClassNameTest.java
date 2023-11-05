package com.example;

import static org.junit.Assert.assertTrue;

public class ClassNameTest extends NamingConventionTest {
    
    private Object classObject;
    private String className;

    public ClassNameTest(String className){
        super();
        this.className = className;
        
    

    }
    public void test(){

        classObject = findClassInstance(className);
        
        if (classObject == null){
            assertTrue(false);
        }
        else{
            assertTrue(true);
            System.out.println(className + " exists!");
        }


    }

    public static void main (String[] args){
        ClassNameTest c  = new ClassNameTest("CeilingFan");
        c.test();
    }

}
