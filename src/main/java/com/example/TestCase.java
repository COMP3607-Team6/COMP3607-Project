package com.example;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.lang.reflect.Field;

/**
 * Abstraction of Test classes
*/
public abstract class TestCase{

    
    private Instances instances;
    /**
    * Arrayist of Instantiated Concrete class objects
    */
    protected ArrayList<Object> testResult;
    /**
    * Arrayist of All class objects
    */
    protected ArrayList<Class<?>> allClasses;
    /**
    * Arraylist Abstract class objects
    */
    protected ArrayList<Class<?>> allAbstractClasses;
    /**
    * Arrayist Interface class objects
    */
    protected ArrayList<Class<?>> allInterfaceClasses;
    /**
    * Arrayist Concrete class objects
    */
    protected ArrayList<Class<?>> allConcreteClasses;
    /**
    * Object which stores test results
    */
    protected TestResult testMarks;
    /**
    * Stores the name of test to be produced
    */
    protected String testName;


    /**
    * Initializes  all arrayLists, instances, TestResult
    */
    public TestCase(int allocatedMarks){
        init();
        this.testMarks = new TestResult(allocatedMarks);

    }

    /**
    * Initializes  arraylists
    */
    public void init(){
        instances = new Instances(); // Initialize the instances variable with a new Instances object
        testResult = instances.getInstances();
        allClasses = instances.getAllClasses();
        allAbstractClasses = instances.getAbstractClasses();
        allInterfaceClasses= instances.getInterfaceClasses();
        allConcreteClasses = instances.getConcreteClasses();

    }

    /**
    *  Resets the testResult Object
    */
    public void reset(){
       testMarks.setTestPassed(false);
       testMarks.setTestComment("");
    }



    public String getTestName(){
        return testName;
    }

    public TestResult getTestMarksObject(){
        return testMarks;
    }
    
    /**
    * Finds an instantiated Object
    */
    public Object oldFindClassInstance(String name){

        for(Object o : testResult){
            String className = o.getClass().getSimpleName();

            if(className.equals(name)){
                return o;
            }
        }

        return null;

    }
    /**
    * Finds class objects
    */
    public Class<?> findClassInstance(String name, ArrayList<Class<?>> classes){

        for(Class<?> c : classes){
            String className = c.getSimpleName();

            if(className.equals(name)){
                return c;
            }
        }

        return null;

    }

    /**
    * Finds Method Object
    */
    public Method findMethodInstance(String className, String methodName, ArrayList<Class<?>> classes){
        Class<?> clazz = findClassInstance(className,classes);
        if (clazz != null){
            try {
                Method method = clazz.getMethod(methodName);
                return method;
            }
            catch (NoSuchMethodException e) {
                return null;
            }
        }
        
        return null;
    }

    /**
    * Find Attribute Object
    */
    public Field findAttributeInstance(String className,String attributeName,ArrayList<Class<?>> classes){
        
        Class<?> clazz = findClassInstance(className,classes);
        
        if (clazz != null){

            try {
                // Get the field with the specified name
                Field field = clazz.getDeclaredField(attributeName);
    
                return field;
            }
            catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
 
        return null;
    }

    /**
    * Runs a jUnit test based on th type of test 
    */
    public abstract String test();
}