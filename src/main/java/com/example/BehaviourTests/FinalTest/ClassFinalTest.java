package com.example.BehaviourTests.FinalTest;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Modifier;

import com.example.BehaviourTests.TypeTest;

public class ClassFinalTest extends TypeTest {

    private Class<?> classObject;
    private String className;

    public ClassFinalTest(String className, int allocatedMarks) {
        super(allocatedMarks);
        this.className = className;
    }

    @Override
    public String test() {
        try{
            classObject = findClassInstance(className, allClasses);
            int modifiers = classObject.getModifiers();
            assertTrue(Modifier.isFinal(modifiers));
            testMarks.setTestPassed(true);
            return "Class Name: " + className + " is of the final type.";
  

        }
        catch(AssertionError e){
            return "Class name:"+ className + " is not of the final Type";
        }
        
    }

    public static void main(String[] args) {
        ClassFinalTest t = new ClassFinalTest("StandingFan", 1);
        System.out.println(t.test());
    }
    
}
