package com.example.NamingConventionTests;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

public class AttributeNameTest extends NamingConventionTest {

    private String className;
    private String attributeName;

    public AttributeNameTest(String className,String attributeName){
        super();
        this.className = className;
        this.attributeName = attributeName;
        
    }

    public String test(){
        try{
        Field f = findAttributeInstance(className, attributeName, allClasses);
        assertTrue(f!= null);
        return "Attribute: " + attributeName + " Found!";
        }
        catch(AssertionError e){
            return "Attribute: " + attributeName + " not Found!";
        }
    }
    public static void main (String[] args){
        AttributeNameTest a  = new AttributeNameTest("Room", "devices");
        System.out.println(a.test());
    }
}
