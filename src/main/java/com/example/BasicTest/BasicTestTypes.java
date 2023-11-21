package com.example.BasicTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

 /**
 * Strategy Interface for Basic Test
*/
public interface BasicTestTypes {

    /**
    * Retrieves the relevant class test based on the test type
    */
    public boolean classTest(Class<?> classObject);
     /**
    * Retrieves the relevant method test based on the test type
    */
    public boolean methodTest(Method methodObject);
     /**
    * Retrieves the relevant attribute test based on the test type
    */
    public boolean attributeTest(Field fieldObject);
    /**
    * Returns test success message
    */
    public String getSuccessMessage();

    /**
    * Returns test fail message
    */
    public String getErrorMessage();

     /**
    * Swaps test naming convention for class tests
    */
    public String classTestNameSwap();

     /**
    * Swaps test naming convention for method tests
    */
    public String methodTestNameSwap();

     /**
    * Swaps test naming convention for attribute tests
    */
    public String attributeTestNameSwap();
    
}
