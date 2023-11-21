package com.example.HierarchyTests;

import static org.junit.Assert.assertTrue;

/**
 * Concrete Class of Hierarchy Test which checks if a subclass extends a superclass.
*/
public class SubClassTest extends HierarchyTest{

    private String subClass;
    private String superClass;

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

}
