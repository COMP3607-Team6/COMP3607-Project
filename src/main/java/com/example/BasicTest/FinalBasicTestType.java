package com.example.BasicTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class FinalBasicTestType implements BasicTestTypes {


    @Override
    public boolean classTest(Class<?> classObject) {
        int modifiers = classObject.getModifiers();
        return Modifier.isFinal(modifiers);
    }

    @Override
    public boolean methodTest(Method methodObject) {
        int modifiers = methodObject.getModifiers();
        return Modifier.isFinal(modifiers);
    }

    @Override
    public boolean attributeTest(Field fieldObject) {
        int modifiers = fieldObject.getModifiers();
        return Modifier.isFinal(modifiers);
    }

    @Override
    public String getSuccessMessage() {
          return " is of the final type."; 
    }

    @Override
    public String getErrorMessage() {
        return " is not of the final Type";
    }

    public String classTestNameSwap(){

        return "Final Type Test for class ";
    }
    public String methodTestNameSwap(){

        return "Final Type Method Test for method ";
    }
    public String attributeTestNameSwap(){

        return "Final Type Attribute Test for attribute ";
    }
    
}
