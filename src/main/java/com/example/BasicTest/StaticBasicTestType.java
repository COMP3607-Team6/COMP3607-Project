package com.example.BasicTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class StaticBasicTestType implements BasicTestTypes {

      @Override
    public boolean classTest(Class<?> classObject) {
        int modifiers = classObject.getModifiers();
        return Modifier.isStatic(modifiers);
    }

    @Override
    public boolean methodTest(Method methodObject) {
        int modifiers = methodObject.getModifiers();
        return Modifier.isStatic(modifiers);
    }

    @Override
    public boolean attributeTest(Field fieldObject) {
        int modifiers = fieldObject.getModifiers();
        return Modifier.isStatic(modifiers);
    }

    @Override
    public String getSuccessMessage() {
          return " is of the static type."; 
    }

    @Override
    public String getErrorMessage() {
        return " is not of the static type";
    }
    
}
