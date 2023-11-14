package com.example.BehaviourTests;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.example.TestCase;

public class AttributeAccessorTypeTest extends AccessorTypeTest {

    private String className; 
    private String accessorType;
    private String attributeName;

    public AttributeAccessorTypeTest(String className,String attributeName, String accessortype){
        super();
        this.className = className;
        this.accessorType = accessortype;
        this.attributeName = attributeName;

    }
    public String test(){
        try{
            int classModifiers = getModifier();
        
            if (accessorType  == "public") {
            assertTrue(Modifier.isPublic(classModifiers));
            return "Public Attribute: " + attributeName + " Found.";
            } 
            else if (accessorType == "private") {
            assertTrue(Modifier.isPrivate(classModifiers));
            return "Private Attribute: " + attributeName + " Found.";
            } 
            else if (accessorType == "protected") {
            assertTrue(Modifier.isProtected(classModifiers));
            return "Protected Attribute: " + attributeName + " Found.";
            } 
            return "Attribute not of Accessor type private, public or protected!";
        
      }
      catch(NullPointerException e){
        return "Attribute Name:" + attributeName + " not found.";
      }
      catch(AssertionError a){
        return "Expected Attribute" + attributeName + " accessor type to be "+ accessorType +  " but found otherwise. ";
      }
    }

    @Override
    public int getModifier() {
        Field field = findAttributeInstance(className, attributeName, allClasses);
        return field.getModifiers();
    }
    
        public static void main (String[] args){
            TestCase t = new AttributeAccessorTypeTest("Room","devices" ,"private");
            String r = t.test();
            System.out.println(r);
        }
    
}
