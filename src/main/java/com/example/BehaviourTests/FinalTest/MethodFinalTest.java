package com.example.BehaviourTests.FinalTest;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.example.BehaviourTests.TypeTest;

public class MethodFinalTest extends TypeTest {

    private Method methodObject;
    private String className;
    private String methodName;

    public MethodFinalTest(String className, String methodName,int allocatedMarks) {
        super(allocatedMarks);
        this.className = className;
        this.methodName = methodName;

        
    }

    @Override
    public String test() {
        try{
            methodObject = findMethodInstance(className, methodName, allClasses);
            int modifiers = methodObject.getModifiers();
            
            assertTrue(Modifier.isFinal(modifiers));
            testMarks.setTestPassed(true);

            return "Method: " + methodName + " was found to be final";

        }
        catch(AssertionError a){
            return "Method: " + methodName + " is not final";
        }

    }
    

    public static void main(String[] args) {
        MethodFinalTest m = new MethodFinalTest("AC","coolsBy",1);
        System.out.println(m.test());
    }
}
