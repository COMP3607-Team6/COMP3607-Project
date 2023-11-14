
package com.example.BehaviourTests;

import static org.junit.Assert.assertTrue;
import java.lang.reflect.Modifier;
import com.example.TestCase;

public class ClassAccessorTypeTest extends AccessorTypeTest{


   private String className; 
   private String accessorType;

    public ClassAccessorTypeTest(String className, String accessorType){
        super();
        this.className = className;
        this.accessorType = accessorType;
    }
   
    public String test() {
      try{
        int classModifiers = getModifier();
      
        if (accessorType == "public") {
          assertTrue(Modifier.isPublic(classModifiers));
          return "Public Class Name: " + className + " Found.";
        } 
        else if (accessorType == "private") {
          assertTrue(Modifier.isPrivate(classModifiers));
          return "Private Class Name: " + className + " Found.";
        } 
        else if (accessorType == "protected") {
          assertTrue(Modifier.isProtected(classModifiers));
          return "Protected Class Name: " + className + " Found.";
        } 
        return "Class not of Accessor type private, public or protected!";
      
      }
      catch(NullPointerException e){
        return "Class Name:" + className + " not found.";
      }
      catch(AssertionError a){
        return "Expected Class " + className + " accessor type to be "+ accessorType +  " but found otherwise. ";
      }
  }

  @Override
  public int getModifier(){
    Class<?> clazz= findClassInstance(className, allClasses);
    int classModifiers = clazz.getModifiers();
    return classModifiers;
  }


  public static void main (String[] args){
    TestCase t = new ClassAccessorTypeTest("CeilingFan", "public");
    String r = t.test();
    System.out.println(r);
  }

}