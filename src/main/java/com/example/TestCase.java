package com.example;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.lang.reflect.Field;

public abstract class TestCase{

    
    private Instances instances;
    protected ArrayList<Object> testResult;
    protected ArrayList<Class<?>> allClasses;
    protected ArrayList<Class<?>> allAbstractClasses;
    protected ArrayList<Class<?>> allInterfaceClasses;
    protected ArrayList<Class<?>> allConcreteClasses;
    protected TestResult testMarks;
    protected String testName;


    public TestCase(int allocatedMarks){
        instances = new Instances(); // Initialize the instances variable with a new Instances object
        testResult = instances.getInstances();
        allClasses = instances.getAllClasses();
        allAbstractClasses = instances.getAbstractClasses();
        allInterfaceClasses= instances.getInterfaceClasses();
        allConcreteClasses = instances.getInterfaceClasses();
        this.testMarks = new TestResult(allocatedMarks);

        System.out.println("Instances " + instances.toString());
        System.out.println("NUMMMM " + testResult.size());

    }

    public String getTestName(){
        return testName;
    }

    public TestResult getTestMarksObject(){
        return testMarks;
    }
    
    
    public Object oldFindClassInstance(String name){

        for(Object o : testResult){
            String className = o.getClass().getSimpleName();

            if(className.equals(name)){
                return o;
            }
        }

        return null;

    }
       public Class<?> findClassInstance(String name, ArrayList<Class<?>> classes){

        for(Class<?> c : classes){
            String className = c.getSimpleName();

            if(className.equals(name)){
                return c;
            }
        }

        return null;

    }

    public Method findMethodInstance(String className, String methodName, ArrayList<Class<?>> classes){
        Class<?> clazz = findClassInstance(className,classes);
        if (clazz != null){
            // Class<?> clazz = classObject.getClass();

            try {
                Method method = clazz.getMethod(methodName);
                
                // Object result = method.invoke(classObject); //Note: This method allows you to call the found method, with parameters etc.
                
                return method;
            } 
            catch (NoSuchMethodException e) {
                return null;
               // e.printStackTrace(); // Handle the exceptions according to your use case
            }
        }
        
        return null;
    }

    public Field findAttributeInstance(String className,String attributeName,ArrayList<Class<?>> classes){
        
        Class<?> clazz = findClassInstance(className,classes);
        
        if (clazz != null){

            try {
                // Get the field with the specified name
                Field field = clazz.getDeclaredField(attributeName);
    
                // System.out.println("Attribute found: " + field.getName());
                return field;
            } 
            catch (NoSuchFieldException e) {
                // System.out.println("Attribute not found: " + attributeName);
            }
        }
 
        return null;
    }

    public abstract String test();
}