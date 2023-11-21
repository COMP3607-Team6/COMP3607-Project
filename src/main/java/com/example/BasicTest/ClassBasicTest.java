package com.example.BasicTest;
import static org.junit.Assert.assertTrue;

/**
 * Concrete Class of BasicTests that handles test for classes
*/
public class ClassBasicTest extends BasicTests {

    private Class<?> classObject;
    private String className;

    public ClassBasicTest(int allocatedMarks,String className, String testType) {
        super(allocatedMarks, testType);
        this.className = className;
        this.testName = testTypeObject.classTestNameSwap() + className;
    }

    

    public String test(){

        classObject = findClassInstance(className,allClasses);
        
        try{
            assertTrue( testTypeObject.classTest(classObject));
            testMarks.setTestPassed(true);
            testMarks.setTestComment("Class " + className + testTypeObject.getSuccessMessage());
            return "Class Name: " + className + testTypeObject.getSuccessMessage();
        }
        catch(AssertionError | NullPointerException n){
            testMarks.setTestComment("Class " + className + testTypeObject.getErrorMessage());
            return "Class Name:" + className + testTypeObject.getErrorMessage();
        }


    }

    @Override
    public String toString() {
            return "{" +
                " classObject='" + classObject + "'" +
                ", className='" +  className + "'" +
                ", marksObject='" +  testMarks.toString() + "'" +
                ", marks='" +  testMarks.getTestMarks() + "'" +
                ", case passed='" +  testMarks.getTestPassed() + "'" +
                "}";
        }
    
}
