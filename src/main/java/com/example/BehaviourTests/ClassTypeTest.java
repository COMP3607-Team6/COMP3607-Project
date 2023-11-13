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
    
    }

    @Override
    public String test() {
        try{

            if(classType == "interface"){
                classObject = findClassInstance(className, allInterfaceClasses );
                assertTrue(classObject != null);
                testMarks.setTestPassed(true);
                return "Class Name: " + className + " is an interface.";

            }
            else if(classType == "abstract"){
                classObject = findClassInstance(className, allAbstractClasses);
                assertTrue(classObject != null);
                testMarks.setTestPassed(true);
                return "Class Name: " + className + " is an abstract class.";


            }
            else{
                classObject = findClassInstance(className,allConcreteClasses);
                assertTrue(classObject != null);
                testMarks.setTestPassed(true);
                return "Class Name: " + className + " is a concrete class.";

            }
        }
        catch(AssertionError a){
            return "Class Name: " + className + " is not of the expected type";
        }
    }


    public static void main(String[] args) {
        ClassTypeTest c = new ClassTypeTest("Fan", 1, "abstract");
        System.out.println(c.test());
    }
    
}
