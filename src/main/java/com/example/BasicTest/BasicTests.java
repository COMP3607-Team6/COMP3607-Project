package com.example.BasicTest;

import com.example.TestCase;

public abstract class BasicTests extends TestCase {

    protected BasicTestTypes testTypeObject;

    public BasicTests(int allocatedMarks, String testType) {
        super(allocatedMarks);
        setBasicTestType(testType);
    }

    public void setBasicTestType(String testType){
        if (testType == "final"){
            this.testTypeObject = new FinalBasicTestType();
        }
        else if(testType == "static"){
            this.testTypeObject = new StaticBasicTestType();
        }
        else if(testType == "name"){
            this.testTypeObject = new NamingConventionBasicTestType();
        }
        else if(testType == "public"){
            this.testTypeObject = new PublicBasicTestType();
        }
        else if(testType == "private"){
            this.testTypeObject = new PrivateBasicTestType();
        }
        else if(testType == "protected"){
            this.testTypeObject = new ProtectedBasicTestType();
        }
        
    }
    
}
