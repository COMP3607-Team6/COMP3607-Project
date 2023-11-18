package com.example;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Instances {
        private ArrayList<Object> a = new ArrayList<>();
        ArrayList<Class<?>> abstractClasses = new ArrayList<>();
        ArrayList<Class<?>> interfaceClasses = new ArrayList<>();
        ArrayList<Class<?>> concreteClasses = new ArrayList<>();
        ArrayList<Class<?>> allClasses = new ArrayList<>();

    public Instances()
    {
        this.setClasses();
    }
        
    public void createInstances(){
        String folderPath = "src\\main\\java\\com\\example\\StudentFile"; // Replace this with the path to your folder containing .java files

        System.out.println(folderPath);

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
                    // System.out.println("HIHIHIdvdsvrd: " + className);
                    Class<?> clazz = Class.forName("com.example.StudentFile." + className);
                    System.out.println("JIJIJIVYVIUV::::::" + clazz.getSimpleName());
                    
                    // Get the constructor of the class (assuming it has a default constructor)
                    //Constructor<?> constructor = clazz.getDeclaredConstructor();

                    Constructor<?>[] constructors = clazz.getDeclaredConstructors();

                    for (Constructor<?> constructor : constructors) {
                        if(constructor.getParameterCount() == 0){
                           Object obj = constructor.newInstance();
                           a.add(obj);
                          // System.out.println(obj.toString());
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
                            a.add(instance);
                            // System.out.println(instance.toString());
                            //break;
                        }
                    }
                    
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

    public String testText(){
        return "Hello";
    }
    public ArrayList<Object> getInstances(){
        createInstances();
        return this.a;
    }

    public void setClasses()
    {
        // Create a URLClassLoader with the folder path
        try (URLClassLoader classLoader = new URLClassLoader(new URL[]{new File("src\\main\\java\\com\\example\\StudentFile").toURI().toURL()})) {
        // Use Files.walk to traverse the folder
        try (Stream<Path> paths = Files.walk(Paths.get("src\\main\\java\\com\\example\\StudentFile"))) {
            paths
            .filter(Files::isRegularFile)
            .filter(p -> p.toString().endsWith(".java"))
            .forEach(p -> {
                // Get the file name without the extension
                String fileName = p.getFileName().toString().replace(".java", "");
                try {
                // Load the class using the class loader
                Class<?> clazz = classLoader.loadClass("com.example.StudentFile." + fileName);
                allClasses.add(clazz);
                // Check if the class is abstract
                if (Modifier.isAbstract(clazz.getModifiers()) && !Modifier.isInterface(clazz.getModifiers())) {
                    // Print the file name and the abstract modifier
                    abstractClasses.add(clazz);
                    // System.out.println(fileName + " is abstract");
                } 
                else if (Modifier.isInterface(clazz.getModifiers()))
                    interfaceClasses.add(clazz);
                else{
                    concreteClasses.add(clazz);
                    
                }
                } catch (ClassNotFoundException e) {
                System.out.println("Class not found: " + fileName);
                }
            });
        }
        } catch (IOException e) {
        System.out.println("Error creating class loader: " + e.getMessage());
        }
        // return abstractClasses;      
    }

    public ArrayList<Class<?>> getAbstractClasses(){
        return this.abstractClasses;
    }

    public ArrayList<Class<?>> getInterfaceClasses(){
        return this.interfaceClasses;
    }

    public ArrayList<Class<?>> getConcreteClasses(){
        return this.concreteClasses;
    }

    public ArrayList<Class<?>> getAllClasses(){
        return this.allClasses;
    }

    public static void main(String[] args) {
        // Instances i = new Instances();
        // i.abstractClasses = i.getAbstractClasses();
        
        // System.out.println("Abstract Classes");
        // for (Class<?> j : i.getAbstractClasses()){
        //     System.out.println("Abstract " + j.getSimpleName());
        //     Field[] fields = j.getDeclaredFields();
        //     for (Field field : fields) {
        //         System.out.println("Attribute: " + field.getName());
        //     }
        // }

        // System.out.println("Interface Classes");
        // for (Class<?> j : i.getInterfaceClasses()){
        //     System.out.println("Interface " + j.getSimpleName());
        //     Field[] fields = j.getDeclaredFields();
        //     for (Field field : fields) {
        //         System.out.println("Attribute: " + field.getName());
        //     }
        // }

        // System.out.println("Concrete Classes");
        // for (Class<?> j : i.getConcreteClasses()){
        //     System.out.println("Concrete " + j.getSimpleName());
        //     Field[] fields = j.getDeclaredFields();
        //     for (Field field : fields) {
        //         System.out.println("Attribute: " + field.getName());
        //     }
        // }

        // System.out.println("All Classes");
        // for (Class<?> j : i.getAllClasses()){
        //     System.out.println("All " + j.getSimpleName());
        //   Field[] fields = j.getDeclaredFields();
        //     for (Field field : fields) {
        //         System.out.println("Attribute: " + field.getName());
        //     }
        // }
    }
}