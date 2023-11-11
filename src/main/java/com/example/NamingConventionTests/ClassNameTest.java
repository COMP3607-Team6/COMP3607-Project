package com.example.NamingConventionTests;

import static org.junit.Assert.assertTrue;

public class ClassNameTest extends NamingConventionTest {
    
    private Class<?> classObject;
    private String className;

    public ClassNameTest(String className, int allocatedMarks){
        super(allocatedMarks);
        this.className = className;

    }


    public String test(){

        classObject = findClassInstance(className,allClasses);
        
        

        try{
            System.out.println(toString());
            assertTrue("Class Name Test 1", classObject != null);
            testMarks.setTestPassed(true);
            System.out.println(toString());
            return "Class Name: " + className + " Found.";
        }
        catch(AssertionError e){
            return "Class Name:" + className + " not found.";
        }


    }

    @Override
    public String toString() {
            return "{" +
                " classObject='" + classObject + "'" +
                ", className='" +  className + "'" +
                ", marksObject='" +  testMarks.toString() + "'" +
                ", marks='" +  testMarks.getTestMarks() + "'" +
                ", case passed='" +  testMarks.getTestPassed() + "'" +
                "}";
        }

    public static void main (String[] args){
        ClassNameTest c  = new ClassNameTest("Device",3);
        String value = c.test();
        System.out.println("   -----------------------------");
        System.out.println(value);
    }

}
