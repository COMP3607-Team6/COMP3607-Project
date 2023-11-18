package com.example.BasicTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public interface BasicTestTypes {

    public boolean classTest(Class<?> classObject);
    public boolean methodTest(Method methodObject);
    public boolean attributeTest(Field fieldObject);
    public String getSuccessMessage();
    public String getErrorMessage();
    
}
