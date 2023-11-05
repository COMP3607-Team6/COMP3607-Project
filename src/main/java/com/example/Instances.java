package com.example;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Instances {

    public static void main(String[] args) {
        String folderPath = "C:\\Users\\Avinash Roopnarine\\Desktop\\OOP 2\\COMP3607-Project\\src\\main\\java\\com\\example\\Avinash_Roopnarine_816029635_A2"; // Replace this with the path to your folder containing .java files

        File folder = new File(folderPath);
        File[] javaFiles = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".java");
            }
        });

        if (javaFiles != null) {
            for (File javaFile : javaFiles) {
                String className = javaFile.getName().replace(".java", "");
                try {
                    // Load the class using reflection
                    Class<?> clazz = Class.forName("com.example.Avinash_Roopnarine_816029635_A2." + className);

                    // Get the constructor of the class (assuming it has a default constructor)
                    //Constructor<?> constructor = clazz.getDeclaredConstructor();

                    Constructor<?>[] constructors = clazz.getDeclaredConstructors();

                    for (Constructor<?> constructor : constructors) {
                        if(constructor.getParameterCount() == 0){
                           Object obj = constructor.newInstance();
                           System.out.println(obj.toString());
                           //break;
                        }

                        if (constructor.getParameterCount() > 0) {
                            List<Object> parameterValues = new ArrayList<>();
                            Parameter[] parameters = constructor.getParameters();
                            for (Parameter parameter : parameters) {
                                Class<?> parameterType = parameter.getType();
                                Object defaultValue = getDefaultValueForType(parameterType); 
                                parameterValues.add(defaultValue);    
                            }
                            Object instance = constructor.newInstance(parameterValues.toArray());
                            System.out.println(instance.toString());
                            //break;
                        }
                    }

                    // List<Object> parameterValues = new ArrayList<>();

                    // Parameter[] parameters = constructor.getParameters();
                  
                    // if(parameters.length > 0){
                        // for (Parameter parameter : parameters) {
                        //     Class<?> parameterType = parameter.getType();
                        //     Object defaultValue = getDefaultValueForType(parameterType);
                        //     System.out.println(defaultValue);
                        //     parameterValues.add(defaultValue);    
                        // }
                    //     Object instance = constructor.newInstance(parameterValues.toArray());
                    // }
                    // else{
                    // // Instantiate the object
                    // //Constructor<?> constructor = clazz.getDeclaredConstructor();
                    // //Object obj = constructor.newInstance();
                  //  }

                    // Now 'obj' is an instance of the class specified by the .java file
                    System.out.println("Object of class " + className + " instantiated.");
                } catch (ClassNotFoundException e) {
                    System.out.println("Class not found: " + className);
                } catch (Exception e) {
                    System.out.println("Error instantiating class " + className + ": " + e.getMessage());
                }
            }
        } else {
            System.out.println("No .java files found in the specified folder.");
        }
    }

    private static Object getDefaultValueForType(Class<?> type) {
        if (type == int.class) {
            return 0; // Default value for int
        } else if (type == String.class) {
            return ""; // Default value for String
        } else if (type == double.class) {
            return 0.0; // Default value for double
        } else if (type == boolean.class) {
            return false; // Default value for boolean
        } else if (type == Object.class) {
            return null; // Default value for Object
        } else {
            return null; // Default value for custom or non-primitive types
        }
    }
}