package com.example.BasicTest;

import java.util.HashMap;
import java.util.Map;

import com.example.TestCase;

/**
 * Abstract class based on simple tests for class,methods,attributes. (Context for Strategy Design Pattern)
*/
public abstract class BasicTests extends TestCase {

    protected BasicTestTypes testTypeObject;
    private Map<String, BasicTestTypes> testTypes;

    public BasicTests(int allocatedMarks, String testType) {
        super(allocatedMarks);
        testTypes = initializeTestTypes();
        setBasicTestType(testType);
    }


    private Map<String, BasicTestTypes> initializeTestTypes() {
        Map<String, BasicTestTypes> types = new HashMap<>();
        types.put("final", new FinalBasicTestType());
        types.put("static", new StaticBasicTestType());
        types.put("name", new NamingConventionBasicTestType());
        types.put("public", new PublicBasicTestType());
        types.put("private", new PrivateBasicTestType());
        types.put("protected", new ProtectedBasicTestType());
        return types;
    }

    public void setBasicTestType(String testType){
        this.testTypeObject = testTypes.get(testType);
    }
    
}
