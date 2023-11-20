package com.example.BehaviourTests;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import com.example.TestCase;

public class MethodValueTest extends ValueTest {

    private String methodName;
    private String className;
    private ArrayList<Object> paras;
    private ArrayList <Object> methodparas;
    private Object expectedValue;
    private Object actualValue;
    private Method method;
    
    public MethodValueTest(String methodName, String className, int allocatedMarks, ArrayList<Object> paras, Object expectedValue){
        super(allocatedMarks);
        this.methodName = methodName;
        this.className = className;
        this.paras = paras;
        methodparas = new ArrayList<>();
        this.expectedValue = expectedValue;
         this.testName = "Method Value Test for " + methodName + " belonging to class: " + className;

    }
    

    // Needs more error handling code
    public String test(){

        actualValue = getValue();
       // System.out.println(actualValue);

        if(actualValue.equals(className + " class not found !!")){
            return className + " class not found !!";
        }

         if(actualValue.equals(methodName + " method not found in " + className + " !!")){
            return methodName + " method not found in " + className + " !!";
        }
        
        try{
            assertEquals(expectedValue, actualValue);
            testMarks.setTestPassed(true);
            testMarks.setTestComment("Method " + methodName + " returns the correct return value "+ actualValue + ".");
            return "Method " + methodName + " returns the correct return value "+ actualValue + ".";
      
        }
        catch(AssertionError e){
            testMarks.setTestComment("Method " + methodName + " was expected to return " + expectedValue + " but returned " + actualValue);
            return "Method " + methodName + " was expected to return " + expectedValue + " but returned " + actualValue;
   
        }
        catch(Exception e){
             return "Method " + methodName + " not found in  " + className;
   
        }

    }
    
    
    public Object getValue(){


       
        Class<?> clazz = findClassInstance(className,allClasses);
      
        
        if(clazz == null){
            return className + " class not found !!";
        }

        Method[] methods = clazz.getDeclaredMethods();

        for(Method m: methods){
            if(m.getName() == methodName && m.getParameterCount() == paras.size() ){
                method = m;
            }
        }

        if(method == null){
            return  methodName + " method not found in " + className + " !!";
        }

       // Method method = findMethodInstance(className, methodName, allClasses);
        //System.out.println(method);
        Object instance = oldFindClassInstance(className);

        
        
        Parameter[] parameters = method.getParameters();
        //System.out.println(parameters.length);
        

        int pointer = 0;

        if(parameters.length == 0){
            try {
                Object result = method.invoke(instance, methodparas.toArray());
                return result;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        else{

            for(Parameter p : parameters){
                Class<?> parameterType = p.getType();
                System.out.println(parameterType);
                getParametersForMethod(parameterType, pointer);
                pointer++;
                System.out.println(methodparas.size());
            }

            try {
                Object result = method.invoke(instance, methodparas.toArray());
                return result;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
           


       
        }



       return null;
    }

    public void getParametersForMethod(Class<?>parameterType, int pointer){
        System.out.println(paras.get(pointer).getClass());
        System.out.println(paras.get(pointer));
        if((parameterType == int.class) && (paras.get(pointer)instanceof Integer)){
            methodparas.add(paras.get(pointer));
        }
        if (parameterType == double.class && paras.get(pointer) instanceof Double) {
            methodparas.add(paras.get(pointer));
            pointer++;
        }
        if (parameterType == boolean.class && paras.get(pointer) instanceof Boolean) {
            methodparas.add(paras.get(pointer));
        } 
        if (parameterType == String.class && paras.get(pointer) instanceof String) {
            methodparas.add(paras.get(pointer));
        }
       if (parameterType == float.class && paras.get(pointer) instanceof Float) {
            methodparas.add(paras.get(pointer));
            
        }
        if (parameterType == long.class && paras.get(pointer) instanceof Long) {
            methodparas.add(paras.get(pointer));
            
        }
        if(parameterType.isPrimitive() == false && paras.get(pointer)instanceof String){
            System.out.println("Reach here");

            addInstancePara((String)paras.get(pointer), getSimpleClassName(parameterType.toString()));
           
        }
        System.out.println("Reach here");
    }

    public void addInstancePara(String paras, String p){

        System.out.println(paras);
        System.out.println(p);


        List<Object> ConstructorParameterValues = new ArrayList<>();

        String withoutBraces = paras.trim().replace("{", "").replace("}", "");

        String[] values = withoutBraces.trim().split(",");

        if(paras == "{}"){
            Class<?> clazz = findClassInstance(p, allClasses);
            Constructor<?> constructor;
            try {
                constructor = clazz.getDeclaredConstructor();
                 try {
                   Object obj = constructor.newInstance();
                   methodparas.add(obj);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                  e.printStackTrace();
            } catch (SecurityException e) {
                  e.printStackTrace();
            }
           

        }

        else{

            int pointer = 0;
            
            Class<?> clazz = findClassInstance(p, allClasses);

            Constructor<?>[] constructors = clazz.getDeclaredConstructors();


            for (Constructor<?> constructor : constructors){
                Parameter[] parameters = constructor.getParameters();
                if(constructor.getParameterCount() == values.length){
                for(Parameter pa :parameters){
                    CheckTypeAndAdd(pa.getType(), ConstructorParameterValues, pointer, values);
                    pointer++;
                }
                try {
                    Object instance = constructor.newInstance(ConstructorParameterValues.toArray());
                    methodparas.add(instance);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    break;
                }

            } 
      }  
    }

    public void CheckTypeAndAdd(Class<?> type, List<Object> ConstructorParameterValues, int pointer, String [] values){
        if (type == int.class) {
          ConstructorParameterValues.add(Integer.parseInt(values[pointer]));
        } 
        else if (type == String.class) {
           ConstructorParameterValues.add(values[pointer]);
        } 
        else if (type == double.class) {
          ConstructorParameterValues.add(Double.parseDouble(values[pointer]));
        } 
        else if (type == boolean.class) {
           ConstructorParameterValues.add(Boolean.parseBoolean(values[pointer]));
        } 
        else if (type == long.class) {
           ConstructorParameterValues.add(Long.parseLong(values[pointer]));
        } 
        else if (type == float.class) {
           ConstructorParameterValues.add(Float.parseFloat(values[pointer]));
        }

    }

    public String getSimpleClassName(String fullyQualifiedClassName) {
        int lastDotIndex = fullyQualifiedClassName.lastIndexOf('.');

        if (lastDotIndex != -1 && lastDotIndex < fullyQualifiedClassName.length() - 1) {
            return fullyQualifiedClassName.substring(lastDotIndex + 1);
        } else {
            // The input may not contain a package name
            return fullyQualifiedClassName;
        }
    }

     public static void main (String[] args){
        ArrayList<Object> paras = new ArrayList<>();
        paras.add(5);
        paras.add("{}");
        // paras.add("{10}");
        // paras.add(5);
        // paras.add("{5,5}");
        //paras.add((float)3.2 );

        TestCase t = new MethodValueTest("coolsBy", "A",1,paras, 13);
        String r = t.test();
        System.out.println(r);
    }


}
