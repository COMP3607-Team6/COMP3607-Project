package com.example;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


public abstract class NamingConventionTest implements TestCase {
    private Instances instances;
    private ArrayList<Object> testResult;

    public NamingConventionTest(String name){
        instances = new Instances(); // Initialize the instances variable with a new Instances object
        testResult = instances.getInstances();

    }

    public abstract void test();

    public Object findClassInstance(String name){

        for(Object o : testResult){
            String className = o.getClass().getSimpleName();

            if(className.equals(name)){
                return o;
            }
        }

        return null;

    }

    public Object findMethodInstance(String className, String methodName){
        Object classObject = findClassInstance(className);
        if (classObject != null){
            Class<?> clazz = classObject.getClass();

            try {
                // Find the method with the specified name
                Method method = clazz.getMethod(methodName);
                
                // Invoke the method if found
                Object result = method.invoke(classObject);
                
                return result;
            } 
            catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace(); // Handle the exceptions according to your use case
            }
        }
        
        return null;
    }

} 
