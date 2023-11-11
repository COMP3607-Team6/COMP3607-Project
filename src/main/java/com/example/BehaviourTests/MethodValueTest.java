package com.example.BehaviourTests;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

import com.example.TestCase;

public class MethodValueTest extends TestCase{

    private  String methodName;
    private Object classObject;
    private Object expectedValue;
    private Object[] parameters;

    public MethodValueTest(String methodName, String obj, Object expectedValue, Object... parameters) {
        this.methodName = methodName;
        this.classObject = oldFindClassInstance(obj);
        this.expectedValue = expectedValue;
        this.parameters = parameters;
    }

    @Test
    public String test() {
        try {
            // Get the method with the specified name and parameter types
            Method method;
            if (parameters.length == 0) {
                method = classObject.getClass().getMethod(methodName);
            } 
            else {
                method = classObject.getClass().getMethod(methodName, getParameterTypes(parameters));
            }
            // Invoke the method with the provided parameters
            Object actualValue = method.invoke(classObject, parameters);

            // Assert the expected value and the actual value
            Assert.assertEquals(expectedValue, actualValue);
            return("Pass");

        } catch (AssertionError| NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // e.printStackTrace();
            return "Fail";
        }
        
    }

    // Helper method to get parameter types from parameter values
    private static Class<?>[] getParameterTypes(Object... parameters) {
        Class<?>[] parameterTypes = new Class<?>[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            parameterTypes[i] = parameters[i].getClass();
        }
        return parameterTypes;
    }

    // Example usage
    public static void main(String[] args) {
        // Assuming you have another class with a method named "add"

        // Create a MethodValueTest instance and run the test
        MethodValueTest test = new MethodValueTest("getStartTemp", "Room", -999);
        System.out.println(test.test());
    }
}





// import java.lang.reflect.InvocationTargetException;
// import java.lang.reflect.Method;
// import static org.junit.Assert.assertEquals;
// public class MethodValueTest extends BehaviourTest {
    
    
    // private String instanceName;
    // private String methodName;
    // private Object[] params;
    // private T expectedResult;
    // Object instance;

    // public MethodValueTest(String instanceName, String methodName, T expectedResult, Object... parameters){

    //     this.methodName = methodName;
    //     this.params = parameters;
    //     this.expectedResult = expectedResult;
    // }

    // public String test(){

    //     try{
    //         instance = oldFindClassInstance(instanceName);
    //         testMethod(instance,expectedResult, methodName, params);

    //         return "Pass";
    //     }
    //     catch(Exception e){
    //         return "Null";
    //     }
    // }


    // public static <T> void testMethod(T instance,T expectedResult, String methodName, Object... parameters) {
    //     try {
    //         // Use reflection to get the method
    //         Method method = findMethod(instance.getClass(), methodName, getParameterTypes(parameters));

    //         // Invoke the method with the provided parameters
    //         Object result = method.invoke(instance, parameters);

    //         // Perform the assertion
    //         assertEquals("Unexpected result for method: " + methodName, expectedResult, result);
    //     } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
    //         throw new RuntimeException("Error while testing method: " + methodName, e);
    //     }
    // }

    // private static Method findMethod(Class<?> clazz, String methodName, Class<?>[] parameterTypes)
    // throws NoSuchMethodException {
    //     try {
    //         return clazz.getMethod(methodName, parameterTypes);
    //     } 
    //     catch (NoSuchMethodException e) {
    //         // Try to find the method in the superclass
    //         Class<?> superClass = clazz.getSuperclass();
    //         if (superClass != null) {
    //             return findMethod(superClass, methodName, parameterTypes);
    //         } else {
    //             throw e;
    //         }
    //     }
    // }

    // private static Class<?>[] getParameterTypes(Object[] parameters) {
    //     Class<?>[] parameterTypes = new Class[parameters.length];
    //     for (int i = 0; i < parameters.length; i++) {
    //         parameterTypes[i] = parameters[i].getClass();
    //     }
    //     return parameterTypes;
    // }

//     public static void main(String[] args) {

        
//     }

// }
