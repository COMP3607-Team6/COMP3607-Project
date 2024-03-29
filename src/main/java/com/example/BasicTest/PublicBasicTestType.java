package com.example.BasicTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

 /**
 * Concrete Strategy of BasicTestTypes that handles Public test for classes,methods,attributes
*/
public class PublicBasicTestType implements BasicTestTypes {

    
    @Override
    public boolean classTest(Class<?> classObject) {
        int modifiers = classObject.getModifiers();
        return Modifier.isPublic(modifiers);
    }

    @Override
    public boolean methodTest(Method methodObject) {
        int modifiers = methodObject.getModifiers();
        return Modifier.isPublic(modifiers);
    }

    @Override
    public boolean attributeTest(Field fieldObject) {
        int modifiers = fieldObject.getModifiers();
        return Modifier.isPublic(modifiers);
    }

    @Override
    public String getSuccessMessage() {
          return " found to be of the public accessor type"; 
    }

    @Override
    public String getErrorMessage() {
        return " is not of the public accessor type";
    }

    public String classTestNameSwap(){

        return "Public Class Test for class ";
    }
    public String methodTestNameSwap(){

        return "Public Method Test for method ";
    }
    public String attributeTestNameSwap(){

        return "Public Attribute Test for attribute ";
    }
    
}
