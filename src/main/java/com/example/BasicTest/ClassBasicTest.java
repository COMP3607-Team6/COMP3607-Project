package com.example.BasicTest;
import static org.junit.Assert.assertTrue;


public class ClassBasicTest extends BasicTests {

    private Class<?> classObject;
    private String className;

    public ClassBasicTest(int allocatedMarks,String className, String testType) {
        super(allocatedMarks, testType);
        this.className = className;
    }

    public String test(){

        classObject = findClassInstance(className,allClasses);
        
        try{
            assertTrue( testTypeObject.classTest(classObject));
            testMarks.setTestPassed(true);
            return "Class Name: " + className + testTypeObject.getSuccessMessage();
        }
        catch(AssertionError e){
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
    
    public static void main(String[] args) {
        ClassBasicTest c  = new ClassBasicTest(3,"Device","naming convention");
        String value = c.test();
        System.out.println("   -----------------------------");
        System.out.println(value);
    }
}
