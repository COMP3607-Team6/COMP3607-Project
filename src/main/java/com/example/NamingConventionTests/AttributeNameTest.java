package com.example.NamingConventionTests;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

public class AttributeNameTest extends NamingConventionTest {

    private String className;
    private String attributeName;

    public AttributeNameTest(String className,String attributeName, int allocatedMarks){
        super(allocatedMarks);
        this.className = className;
        this.attributeName = attributeName;
        
    }

    public String test(){
        try{
        System.out.println(toString());
        Field f = findAttributeInstance(className, attributeName, allClasses);
        assertTrue(f!= null);
        testMarks.setTestPassed(true);
        System.out.println(toString());
        return "Attribute: " + attributeName + " Found!";
  
        }
        catch(AssertionError e){
            return "Attribute: " + attributeName + " not Found!";
        }
    }

    @Override
    public String toString() {
            return "{" +
                " attributeName='" + attributeName + "'" +
                ", className='" +  className + "'" +
                ", Marks Object='" +  testMarks.toString() + "'" +
                ", marks='" +  testMarks.getTestMarks() + "'" +
                ", test Pass='" +  testMarks.getTestPassed() + "'" +
                "}";
    }
    public static void main (String[] args){
        AttributeNameTest a  = new AttributeNameTest("Room", "devices", 2);
        System.out.println(a.test());
    }
}
