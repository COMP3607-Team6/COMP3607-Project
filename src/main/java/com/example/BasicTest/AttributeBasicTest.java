package com.example.BasicTest;
import static org.junit.Assert.assertTrue;
import java.lang.reflect.Field;

/**
 * Handles accessorTypeTest, NamingConventionTest and Static/Final Tests for Attributes
*/
public class AttributeBasicTest extends BasicTests {

    private String className;
    private String attributeName;

    public AttributeBasicTest(int allocatedMarks,String className, String attributeName,String testType) {
        super(allocatedMarks,testType);
        this.className = className;
        this.attributeName = attributeName;
        this.testName = testTypeObject.attributeTestNameSwap() + attributeName + " belonging to class " + className;
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
        catch (Exception n){
            testMarks.setTestComment("Attribute: " + attributeName + "not found " + " in " + className);
            return "Attribute: " + attributeName + "not found " + " in " + className;
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
    
}
