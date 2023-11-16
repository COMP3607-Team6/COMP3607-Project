package com.example.BasicTest;
import static org.junit.Assert.assertTrue;
import java.lang.reflect.Method;


public class MethodBasicTest extends BasicTests {
    
    private Method methodObject;
    private String className;
    private String methodName;

    public MethodBasicTest(int allocatedMarks,String className, String methodName,String testType){
        super(allocatedMarks,testType);
        this.className = className;
        this.methodName = methodName;

        
    }
    public String test() {
        try{
            methodObject = findMethodInstance(className, methodName, allClasses);
            assertTrue(testTypeObject.methodTest(methodObject));
            testMarks.setTestPassed(true);
            testMarks.setTestComment("Method " + methodName + testTypeObject.getSuccessMessage()+ " in " + className+ ".java");
            return "Method: " + methodName + testTypeObject.getSuccessMessage() + " in " + className;

        }
        catch(AssertionError a){
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
    public static void main(String[] args) {
        MethodBasicTest m = new MethodBasicTest(1,"AC","coolsBy","final");
        System.out.println(m.test());

        MethodBasicTest p = new MethodBasicTest(1,"AC","getID","public");
        System.out.println(p.test());
    }
}
