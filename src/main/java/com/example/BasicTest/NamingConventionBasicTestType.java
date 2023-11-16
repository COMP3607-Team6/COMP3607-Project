package com.example.BasicTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
    
}
