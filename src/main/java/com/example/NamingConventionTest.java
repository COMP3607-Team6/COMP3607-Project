package com.example;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.lang.reflect.Field;

public abstract class NamingConventionTest implements TestCase {
    private Instances instances;
    private ArrayList<Object> testResult;

    public NamingConventionTest(){
        instances = new Instances(); // Initialize the instances variable with a new Instances object
        testResult = instances.getInstances();

    }

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
                Method method = clazz.getMethod(methodName);
                
                Object result = method.invoke(classObject); //Note: This method allows you to call the found method, with parameters etc.
                
                return result;
            } 
            catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace(); // Handle the exceptions according to your use case
            }
        }
        
        return null;
    }

    public Object findAttributeInstance(String className,String attributeName){
        Object classObject = findClassInstance(className);
        if (classObject != null){

            try {
                Class<?> clazz = classObject.getClass();
    
                // Get the field with the specified name
                Field field = clazz.getDeclaredField(attributeName);
    
                System.out.println("Attribute found: " + field.getName());
                return field;
            } 
            catch (NoSuchFieldException e) {
                System.out.println("Attribute not found: " + attributeName);
            }
        }
            
        
        return null;
    }

} 
