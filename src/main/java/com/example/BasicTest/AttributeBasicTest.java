package com.example.BasicTest;
import static org.junit.Assert.assertTrue;
import java.lang.reflect.Field;


public class AttributeBasicTest extends BasicTests {

    private String className;
    private String attributeName;

    public AttributeBasicTest(int allocatedMarks,String className, String attributeName,String testType) {
        super(allocatedMarks,testType);
        this.className = className;
        this.attributeName = attributeName;
    }

     public String test() {
        try{

            Field f = findAttributeInstance(className, attributeName, allClasses);
            assertTrue(testTypeObject.attributeTest(f));
            testMarks.setTestPassed(true);
            testMarks.setTestComment("Attribute name " + attributeName + testTypeObject.getSuccessMessage()+ " in " + className + ".java");
            return "Attribute: " + attributeName + testTypeObject.getSuccessMessage() + " in " + className;
        }
        catch(AssertionError e){
            testMarks.setTestComment("Attribute name " + attributeName + testTypeObject.getErrorMessage()+ " in " + className+ ".java");
            return "Attribute: " + attributeName + testTypeObject.getErrorMessage()+ " in " + className;
        }
    }
    @Override
    public String toString() {
            return "{" +
                " attributeName='" + attributeName + "'" +
                ", className='" +  className + "'" +
                ", Marks Object='" +  testMarks.toString() + "'" +
                ", marks='" +  testMarks.getTestMarks() + "'" +
                ", test Pass='" +  testMarks.getTestPassed() + "'" +
                "}";
    }
    public static void main(String[] args) {
        AttributeBasicTest a = new AttributeBasicTest(1,"Fan", "id","static");
        System.out.println(a.test());
    }

    
}
