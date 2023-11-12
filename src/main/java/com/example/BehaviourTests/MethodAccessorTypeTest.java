package com.example.BehaviourTests;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.example.TestCase;

public class MethodAccessorTypeTest extends AccessorTypeTest {

    private String className;
    private String methodName; 
    private String accessorType;


    public MethodAccessorTypeTest(String className, String methodName,int allocatedMarks, String accessorType) {
        super(allocatedMarks);
        this.className = className;
        this.methodName = methodName;
        this.accessorType = accessorType;
    }


    public String test() {
      try{
        int methodModifiers = getModifier();
      
        if (accessorType == "public") {
          assertTrue(Modifier.isPublic(methodModifiers));
          return "Public Method Name: " + methodName + " Found.";
        } 
        else if (accessorType == "private") {
          assertTrue(Modifier.isPrivate(methodModifiers));
          return "Private Method Name: " + methodName + " Found.";
        } 
        else if (accessorType == "protected") {
          assertTrue(Modifier.isProtected(methodModifiers));
          return "Protected Method Name: " + methodName + " Found.";
        } 
        return "Method not of Accessor type private, public or protected!";
      
      }
      catch(NullPointerException e){
        return "Method Name:" + methodName+ " not found.";
      }
      catch(AssertionError a){
        //a.printStackTrace();
        return "Expected Method " + methodName + " accessor type to be "+ accessorType +  " but found otherwise. ";
      }
  }

    @Override
    public int getModifier(){
        Class<?> clazz= findClassInstance(className, allClasses);
        Method method;
        try {
            method = clazz.getMethod(methodName);
            int methodModifiers = method.getModifiers();
            return methodModifiers;
        }catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            System.out.println("No method named " + methodName + " was found in " + className);
        }
        return 0;
    } 

    public static void main (String[] args){
        TestCase t = new MethodAccessorTypeTest("AC","getID",1, "public");
        String r = t.test();
        System.out.println(r);
    }

}
