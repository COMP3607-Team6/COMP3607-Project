package com.example.BasicTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

 /**
 * Concrete Strategy of BasicTestTypes that handles Naming Convention test for classes,methods,attributes
*/
public class NamingConventionBasicTestType implements BasicTestTypes {

    public boolean classTest(Class<?> classObject){

        return classObject != null;
    }

    public boolean methodTest(Method methodObject){

        return methodObject != null;
    }
    
    public boolean attributeTest(Field fieldObject){

        return fieldObject!= null;
    }

    @Override
    public String getSuccessMessage() {
            return " found";
        }

    @Override
    public String getErrorMessage() {
        return " not found.";
    }

    public String classTestNameSwap(){

        return "Naming Convention Class Test for class ";
    }
    public String methodTestNameSwap(){

        return "Naming Convention Method Test for method ";
    }
    public String attributeTestNameSwap(){

        return "Naming Convention Attribute Test for attribute ";
    }
    
}
