package com.example.BasicTest;
import static org.junit.Assert.assertTrue;
import java.lang.reflect.Method;

 /**
 * Concrete Class of BasicTests that handles test for classes
*/
public class MethodBasicTest extends BasicTests {
    
    private Method methodObject;
    private String className;
    private String methodName;

    public MethodBasicTest(int allocatedMarks,String className, String methodName,String testType){
        super(allocatedMarks,testType);
        this.className = className;
        this.methodName = methodName;
        this.testName = testTypeObject.methodTestNameSwap() + methodName + " belonging to " + className;

        
    }
    public String test() {
        try{
            methodObject = findMethodInstance(className, methodName, allClasses);
            assertTrue(testTypeObject.methodTest(methodObject));
            testMarks.setTestPassed(true);
            testMarks.setTestComment("Method " + methodName + testTypeObject.getSuccessMessage()+ " in " + className+ ".java");
            return "Method: " + methodName + testTypeObject.getSuccessMessage() + " in " + className;

        }
        catch(AssertionError| NullPointerException a){
            testMarks.setTestComment("Method " + methodName + testTypeObject.getErrorMessage()+ " in " + className+ ".java");
            return "Method: " + methodName + testTypeObject.getErrorMessage() + " in " + className;
        }

    }
    @Override
    public String toString() {
            return "{" +
                " methodName='" + methodName + "'" +
                ", className='" +  className + "'" +
                ", marksObject='" +  testMarks.toString() + "'" +
                ", marks='" +  testMarks.getTestMarks() + "'" +
                ", Test pass='" +  testMarks.getTestPassed() + "'" +
                "}";
    }
}
