package com.example.AssignmentSpecificationPortal;

import java.util.ArrayList;

public class ClassInformation {
    //
    /* This class holds all the information of saved Classes in the GUI
     */
    private final String accessType;
    private final String isAbstract;
    private final String isInterface;
    private final String className;
    private final String extendsOrImplements;
    private final String extendedOrImplementedClass;
   // private final String marks;
    private ArrayList<MethodInformation> methods;
    private ArrayList<AttributeInformation> attributes;

    public ClassInformation(String accessType, String isAbstract, 
                            String isInterface, String className,
                            String extendsOrImplements, 
                            String extendedOrImplementedClass) {
        this.accessType = accessType;
        this.isAbstract = isAbstract;
        this.isInterface = isInterface;
        this.className = className;
        this.extendsOrImplements = extendsOrImplements;
        this.extendedOrImplementedClass = extendedOrImplementedClass;
        //this.marks = marks;
        this.methods = new ArrayList<MethodInformation>();
        this.attributes = new ArrayList<AttributeInformation>();
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

    public String getType() {
        String result="";
        if(isInterface=="interface"){
            result =  isInterface;
        }
        else{
           result =  isAbstract;
        }
        
        return result;
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

   /*  public String getMarks() {
        return marks;
    } */

    public ArrayList<AttributeInformation> addAttribute(AttributeInformation attribute) {
        attributes.add(attribute);

        return attributes;
    }

    
    public ArrayList<AttributeInformation> getAttributes() {
        return attributes;
    }

    public ArrayList<MethodInformation> addMethod(MethodInformation method) {
        methods.add(method);

        return methods;
    }
    public ArrayList<MethodInformation> getMethods() {
        return methods;
    }

    @Override
    public String toString() {
        
        String result = accessType + " " + isAbstract + " " + isInterface + " " + className;

        if (extendsOrImplements != null && !extendsOrImplements.isEmpty() &&
            extendedOrImplementedClass != null && !extendedOrImplementedClass.isEmpty()) {
            result += " " + extendsOrImplements + " " + extendedOrImplementedClass;
        }

       // result += " [ " + marks + " marks]";

       result = result.replaceAll("\\s{2,}", " ");
        return result;
    }
}