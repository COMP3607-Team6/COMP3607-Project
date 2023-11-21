package com.example.AssignmentSpecificationPortal;

public class MethodInformation {
    //
    /* This class holds all the information of saved Methods in the GUI
     */
    private final String accessType;
    private final String isAbstract;
    private final String methodType;
    private final String methodName;
    private final String methodParameters;//or maybe send the arraylist?
    private final String marks;

    public MethodInformation(String accessType, String isAbstract, String methodType, String methodName, String methodParameters, String marks){
        this.accessType = accessType;
        this.isAbstract = isAbstract;
        this.methodType = methodType;
        this.methodName = methodName;
        this.methodParameters = methodParameters;
        this.marks = marks;
    }

    //accessors
    public String getAccessType(){
        return accessType;
    }
    public String getIsAbstract(){
        return isAbstract;
    }
    public String getMethodType(){
        return methodType;
    }
    public String getMethodName(){
        return methodName;
    }
    public String getMethodParameters(){
        return methodParameters;
    }
    public String getMarks(){
        return marks;
    }

    @Override
    public String toString(){
        String methodSignature = getAccessType() + " " + getIsAbstract() + " " + getMethodType() + " " + getMethodName() + "("+ getMethodParameters() + ")";

        return methodSignature;
    }
}
