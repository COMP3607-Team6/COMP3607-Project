package com.example.BasicTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class PrivateBasicTestType implements BasicTestTypes {
    
    @Override
    public boolean classTest(Class<?> classObject) {
        int modifiers = classObject.getModifiers();
        return Modifier.isPrivate(modifiers);
    }

    @Override
    public boolean methodTest(Method methodObject) {
        int modifiers = methodObject.getModifiers();
        return Modifier.isPrivate(modifiers);
    }

    @Override
    public boolean attributeTest(Field fieldObject) {
        int modifiers = fieldObject.getModifiers();
        return Modifier.isPrivate(modifiers);
    }

    @Override
    public String getSuccessMessage() {
          return " found to be of the private accessor type"; 
    }

    @Override
    public String getErrorMessage() {
        return " is not of the private accessor type";
    }
}
