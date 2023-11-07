package com.example.HierarchyTests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SubTypeTest extends HierarchyTest {
    private Object classObject;
    private String superClassPath = "src\\main\\java\\com\\example\\Avinash_Roopnarine_816029635_A2";
    String subClass; String superClass;

    public SubTypeTest(String subClass, String superClass)
    {
        super();
        this.subClass = subClass;
        this.superClass = superClass;
    }

    public String test() {

    //Specify folder with java files
    Path superClasspath = Paths.get(superClassPath, superClass+".java");

    if (Files.exists(superClasspath)) 
    {
      classObject = oldFindClassInstance(subClass);
      if (classObject != null)
      {

        try {
            URL url = superClasspath.toUri().toURL();
            URLClassLoader classLoader = new URLClassLoader(new URL[]{url});
            
            String packageName = "com.example.Avinash_Roopnarine_816029635_A2."; //Can be made a parameter
            
            Class<?> deviceClass = classLoader.loadClass(packageName + superClass);
            classLoader.close();
            // System.out.println(deviceClass1.getClasses());
    
            try {
                // assertTrue(deviceClass.isInterface());
                assertTrue(deviceClass.isAssignableFrom(classObject.getClass()));
                return classObject.getClass().getSimpleName() + " implements " + deviceClass.getSimpleName();
            }
            catch (AssertionError e)
            {
                return classObject.getClass().getSimpleName() + " does not implement " + deviceClass.getSimpleName();
            }
            } 
            catch (MalformedURLException e) 
            {
                // fail("MalformedURLException: " + e.getMessage());
                return "MalformedURLException: " + e.getMessage();
            } 
            catch (ClassNotFoundException e) 
            {
                // fail("ClassNotFoundException: " + e.getMessage());
                return "ClassNotFoundException: " + e.getMessage();
            }
            catch (IOException e)
            {
                return "IOException: " + e.getMessage();
            }
       }
    }

    return "Test Failed";
}

    
    public static void main (String[] args)
    {
        // Object hi = getSuperClass("Fan");
        SubTypeTest t = new SubTypeTest("AC", "Device");
        
        // System.out.println(t.test());
        System.out.println(t.test());
        // t.test1();
    }
}