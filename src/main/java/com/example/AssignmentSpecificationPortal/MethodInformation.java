package com.example.AssignmentSpecificationPortal;

/**
 * This class holds all the information of saved Methods in the GUI
 */
public class MethodInformation {

    private final String accessType;
    private final String isAbstract;
    private final String isStatic;
    private final String isFinal;
    private final String methodType;
    private final String methodName;
    private final String methodParameters;
   

   
    public MethodInformation(String accessType, String isAbstract, String methodType, String methodName, String methodParameters, String isStatic, String isFinal){
        this.accessType = accessType;
        this.isAbstract = isAbstract;
        this.methodType = methodType;
        this.methodName = methodName;
        this.methodParameters = methodParameters;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
        
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
    
   
    @Override
    public String toString(){
        String methodSignature = getAccessType();
        
        if(getIsAbstract() != "concrete") {
            methodSignature += " " + getIsAbstract();
        }

       methodSignature += " " + getIsStatic() + " " +  getIsFinal() + " " +  getMethodType() + " " + getMethodName() + "("+ getMethodParameters() + ")";
       
        methodSignature = methodSignature.replaceAll("\\s{2,}", " ");
        
        
     return methodSignature;
    }

}

