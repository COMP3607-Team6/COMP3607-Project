package com.example.AssignmentSpecificationPortal;
import java.util.ArrayList;
public class ClassInformation {
    private final String accessType;
    private final String isAbstract;
    private final String isInterface;
    private final String className;
    private final String extendsOrImplements;
    private final String extendedOrImplementedClass;
    private final String marks;
    private final ArrayList<String> methods;

    // i am thinking this will also have the following:
    // private ArrayList<AttributeInformation> attributes;
    // private ArrayList<MethodsInformation> methods;

    public ClassInformation(String accessType, String isAbstract, 
                            String isInterface, String className,
                            String extendsOrImplements, 
                            String extendedOrImplementedClass, 
                            String marks, ArrayList<String> methods) {
        this.accessType = accessType;
        this.isAbstract = isAbstract;
        this.isInterface = isInterface;
        this.className = className;
        this.extendsOrImplements = extendsOrImplements;
        this.extendedOrImplementedClass = extendedOrImplementedClass;
        this.marks = marks;
        this.methods = methods;
    }

    public String getAccessType() {
        return accessType;
    }

    public String getIsAbstract() {
        return isAbstract;
    }

    public String getIsInterface() {
        return isInterface;
    }

    public String getClassName() {
        return className;
    }

    public String getExtendsOrImplements() {
        return extendsOrImplements;
    }

    public String getExtendedOrImplementedClass() {
        return extendedOrImplementedClass;
    }

    public String getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        String result = accessType + " " + isAbstract + " " + isInterface + " " + className;

        if (extendsOrImplements != null && !extendsOrImplements.isEmpty() &&
            extendedOrImplementedClass != null && !extendedOrImplementedClass.isEmpty()) {
            result += " " + extendsOrImplements + " " + extendedOrImplementedClass;
        }

        result += " [ " + marks + " marks]";
        return result;
    }
}