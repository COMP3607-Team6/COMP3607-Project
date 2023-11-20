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
    private String superClassPath = "src\\main\\java\\com\\example\\StudentFile";
    String subClass; String superClass;

    public SubTypeTest(String subClass, String superClass, int allocatedMarks)
    {
        super(allocatedMarks);
        this.subClass = subClass;
        this.superClass = superClass;
        this.testName = "SubType Test for relationship between subtype class: "+ subClass + " and superclass " + superClass ;
  
    }

    public String test() {

              Class<?> subclassObj = findClassInstance(subClass, allClasses);
              Class<?> superClassObj = findClassInstance(superClass, allInterfaceClasses);

            try {
                
                if (subclassObj == null || subclassObj == null){
                throw new NullPointerException();
                }
   

                assertTrue(superClassObj.isAssignableFrom(subclassObj));
                testMarks.setTestPassed(true);
                testMarks.setTestComment(subclassObj.getSimpleName() + " implements " + superClassObj.getSimpleName());
                return subclassObj.getSimpleName() + " implements " + superClassObj.getSimpleName();
            }
            catch (AssertionError| NullPointerException  e)
            {
                testMarks.setTestComment(subClass + " does not implement " + superClass);
                return subClass + " does not implement " + superClass;
            }
       }
    
    public static void main (String[] args)
    {
        // Object hi = getSuperClass("Fan");
        SubTypeTest t = new SubTypeTest("CeilingFan", "Fan",1);
        
        // System.out.println(t.test());
        System.out.println(t.test());
        // t.test1();
    }
}
