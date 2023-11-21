package com.example.HierarchyTests;

import static org.junit.Assert.assertTrue;
/**
 * Concrete Class of Hierarchy Test which checks if a subclass implements a superclass.
*/
public class SubTypeTest extends HierarchyTest {
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
    
}
