package com.example;

import static org.junit.Assert.assertTrue;

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
        Object object = findAttributeInstance(className, attributeName);
        assertTrue(object != null);
        return "Attribute: " + attributeName + "Found!";
        }
        catch(AssertionError e){
            return "Attribute: " + attributeName + " not Found!";
        }
    }
    public static void main (String[] args){
        AttributeNameTest a  = new AttributeNameTest("Room", "devices");
        a.test();
    }
}
