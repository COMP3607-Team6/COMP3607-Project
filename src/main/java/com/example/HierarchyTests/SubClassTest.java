package com.example.HierarchyTests;

import static org.junit.Assert.assertTrue;


// import com.example.Avinash_Roopnarine_816029635_A2.Fan;

import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class SubClassTest extends HierarchyTest{

    private Object classObject;
    private String superClassPath = "src\\main\\java\\com\\example\\StudentFile";
    String subClass; String superClass;

    public SubClassTest(String subClass, String superClass, int allocatedMarks)
    {
        super(allocatedMarks);
        this.subClass = subClass;
        this.superClass = superClass;
        this.testName = "Subclass Test for relationship between subclass: "+ subClass + " and superclass " + superClass ;
    }

public String test() {

    Class<?> subclassObj = findClassInstance(subClass, allClasses);
    Class<?> superClassObj = findExtensibleSuperClass(superClass);

  try {
        if (subclassObj == null || subclassObj == null){
            throw new NullPointerException();
        }


      assertTrue(superClassObj.isAssignableFrom(subclassObj));
      testMarks.setTestPassed(true);
      testMarks.setTestComment(subclassObj.getSimpleName() + " extends " + superClassObj.getSimpleName());
      return subclassObj.getSimpleName() + " extends " + superClassObj.getSimpleName();
  }
  catch (AssertionError| NullPointerException e)
  {
      testMarks.setTestComment(subClass + " does not extends " + superClass);
      return subClass + " does not extends " + superClass;
  }

}

    
    public static void main (String[] args)
    {
        // Object hi = getSuperClass("Fan");
        SubClassTest t = new SubClassTest("CeilingFan", "Fan",1);
        
        // System.out.println(t.test());
        System.out.println(t.test());
        // t.test1();

        
    }
}
