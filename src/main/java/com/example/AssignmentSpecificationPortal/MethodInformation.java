package com.example.AssignmentSpecificationPortal;

public class MethodInformation {
    private  String accessType;
    private  String isAbstract;
    private  String isStatic;
    private  String isFinal;
    private  String methodType;
    private  String methodName;
    private   String methodParameters;//or maybe send the arraylist?
    //private final String marks;

    public MethodInformation(String accessType, String isAbstract, String methodType, String methodName, String methodParameters, String isStatic, String isFinal){
        this.accessType = accessType;
        this.isAbstract = isAbstract;
        this.methodType = methodType;
        this.methodName = methodName;
        this.methodParameters = methodParameters;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
        
        //this.marks = marks;
    }

    //accessors
    public String getAccessType(){
        return this.accessType;
    }
    public String getIsAbstract(){
        return this.isAbstract;
    }
    public String getMethodType(){
        return this.methodType;
    }
    public String getMethodName(){
        return this.methodName;
    }
    public String getMethodParameters(){
        return this.methodParameters;
    }
    public String getIsStatic(){
        return this.isStatic;
    }
    public String getIsFinal(){
        return this.isFinal;
    }
    
    // public String getMarks(){
    //     return marks;
    // }

    ///============================
    @Override
    public String toString(){
        String methodSignature = getAccessType();
        
        if(getIsAbstract() != "concrete") {
            methodSignature += " " + getIsAbstract();
        }

        methodSignature += " " + getIsStatic() + " " +  getIsFinal() + " " +  getMethodType() + " " + getMethodName() + "("+ getMethodParameters() + ")";
        
        methodSignature = methodSignature.replaceAll("\\s{2,}", " ");
        // System.out.println(methodSignature);
        
     return methodSignature;
    }

}

