package com.example.BasicTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ProtectedBasicTestType implements BasicTestTypes {
      
    @Override
    public boolean classTest(Class<?> classObject) {
        int modifiers = classObject.getModifiers();
        return Modifier.isProtected(modifiers);
    }

    @Override
    public boolean methodTest(Method methodObject) {
        int modifiers = methodObject.getModifiers();
        return Modifier.isProtected(modifiers);
    }

    @Override
    public boolean attributeTest(Field fieldObject) {
        int modifiers = fieldObject.getModifiers();
        return Modifier.isProtected(modifiers);
    }

    @Override
    public String getSuccessMessage() {
          return " found to be of the protected accessor type"; 
    }

    @Override
    public String getErrorMessage() {
        return " is not of the protected accessor type";
    }

    public String classTestNameSwap(){

        return "Protected Class Test for class ";
    }
    public String methodTestNameSwap(){

        return "Protected Method Test for method ";
    }
    public String attributeTestNameSwap(){

        return "Protected Attribute Test for attribute ";
    }
}
