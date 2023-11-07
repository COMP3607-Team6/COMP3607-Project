package com.example;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.lang.reflect.Modifier;


// import com.example.Avinash_Roopnarine_816029635_A2.Fan;

import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class SubClassTest extends HierarchyTest{

    private Object classObject;
    private String superClassPath = "src\\main\\java\\com\\example\\Avinash_Roopnarine_816029635_A2";
    String subClass; String superClass;

    public SubClassTest(String subClass, String superClass)
    {
        super();
        this.subClass = subClass;
        this.superClass = superClass;
    }

// @Test
  public String test() {

    //Specify folder with java files
    Path superClasspath = Paths.get(superClassPath, superClass+".java");


    if (Files.exists(superClasspath)) 
    {
      classObject = findClassInstance(subClass);
      if (classObject != null)
      {

        try {
            URL url = superClasspath.toUri().toURL();
            URLClassLoader classLoader = new URLClassLoader(new URL[]{url});
            
            String packageName = "com.example.Avinash_Roopnarine_816029635_A2."; //Can be made a parameter
            Class<?> deviceClass = classLoader.loadClass(packageName + superClass);
            // System.out.println(deviceClass1.getClasses());
            classLoader.close();
            try {
                assertTrue(deviceClass.isAssignableFrom(classObject.getClass()));
                return classObject.getClass().getSimpleName() + " is subclassed from " + deviceClass.getSimpleName();
            }
            catch (AssertionError e)
            {
                return classObject.getClass().getSimpleName() + " is not subclassed from " + deviceClass.getSimpleName();
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
            catch(IOException e){
                return "IOException: " + e.getMessage();
            }
       }
    }
    return "Test Failed";
}

    
    public static void main (String[] args)
    {
        // Object hi = getSuperClass("Fan");
        SubClassTest t = new SubClassTest("StandingFan", "Fan");
        
        // System.out.println(t.test());
        System.out.println(t.test());
        // t.test1();

        
    }
}
