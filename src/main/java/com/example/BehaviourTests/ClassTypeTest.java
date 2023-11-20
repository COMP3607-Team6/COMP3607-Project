package com.example.BehaviourTests;

import static org.junit.Assert.assertTrue;


public class ClassTypeTest extends BehaviourTest {

    private Class<?> classObject;
    private String className;
    private String classType;


    public ClassTypeTest(String className, int allocatedMarks, String classType) {
        super(allocatedMarks);
        this.className = className;
        this.classType = classType;
        this.testName = "Class Type Test for " + className;

    
    }

    @Override
    public String test() {
        try{

            String output = "";

            if(classType == "interface"){
                classObject = findClassInstance(className, allInterfaceClasses );
                assertTrue(classObject != null);
                output = "Class Name: " + className + " is an interface.";

            }
            else if(classType == "abstract"){
                classObject = findClassInstance(className, allAbstractClasses);
                assertTrue(classObject != null);
                output =  "Class Name: " + className + " is an abstract class.";


            }
            else{
                classObject = findClassInstance(className,allConcreteClasses);
                assertTrue(classObject != null);
                
                output =  "Class Name: " + className + " is a concrete class.";

            }

            testMarks.setTestPassed(true);
            testMarks.setTestComment(output);
            return output;

        }
        catch(AssertionError a){
            testMarks.setTestComment("Class Name: " + className + " expected to be of type " + classType + " but found otherwise");
            return "Class Name: " + className + " expected to be of type " + classType + " but found otherwise";
        }
    }


    public static void main(String[] args) {
        ClassTypeTest c = new ClassTypeTest("Fan", 1, "abstract");
        System.out.println(c.test());
    }
    
}
