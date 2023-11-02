package com.example;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;

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
                    Constructor<?> constructor = clazz.getDeclaredConstructor();

                    // Instantiate the object
                    Object obj = constructor.newInstance();

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
}