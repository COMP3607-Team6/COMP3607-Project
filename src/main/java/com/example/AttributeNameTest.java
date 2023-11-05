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

    public void test(){
        Object object = findAttributeInstance(className, attributeName);
        assertTrue("Attribute found: ",object != null);
    }
    public static void main (String[] args){
        AttributeNameTest a  = new AttributeNameTest("Room", "devices");
        a.test();
    }
}
