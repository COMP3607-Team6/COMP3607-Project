package com.example.BehaviourTests.FinalTest;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.example.BehaviourTests.TypeTest;

public class AttributeFinalTest extends TypeTest {

    private String className;
    private String attributeName;

    public AttributeFinalTest(String className, String attributeName, int allocatedMarks) {
        super(allocatedMarks);
        this.className = className;
        this.attributeName = attributeName;
        
    }

    @Override
    public String test() {
        try{

            Field f = findAttributeInstance(className, attributeName, allClasses);
            int modifiers = f.getModifiers();
           
           
            assertTrue(Modifier.isFinal(modifiers));
            testMarks.setTestPassed(true);
            return "Attribute: " + attributeName + " found to be final.";





        }
        catch(AssertionError e){
            return "Attribute: " + attributeName + " is not final!";
        }
    }
    
    public static void main(String[] args) {
        AttributeFinalTest a = new AttributeFinalTest("Fan", "id", 1);
        System.out.println(a.test());
    }


}
