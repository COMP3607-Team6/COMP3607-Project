package com.example.AssignmentSpecificationPortal;

public class AttributeInformation {
    //
    /* This class holds all the information of saved Attributes in the GUI
     */
    private final String accessType;
    private final String isStatic;
    private final String isFinal;
    private final String attributeType;
    private final String attributeName;
    private final String objectName;

    public AttributeInformation(String accessType, String isStatic,String isFinal, String attributeType,  String attributeName, String objectName) {
        this.accessType = accessType;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
        this.attributeType = attributeType;
        this.attributeName = attributeName;
        this.objectName = objectName;
    }

    @Override
    public String toString() {

        String result = accessType + " ";

        if (!isStatic.isEmpty())
            result += isStatic + " ";

        if (!isFinal.isEmpty())
            result += isFinal + " ";

        if (attributeType.equals("object"))
            result += objectName + " ";
        else
            result += attributeType + " ";

        result += attributeName;
        
        result = result.replaceAll("\\s{2,}", " ");

        return result;
      /*   if ("object".equals(attributeType)) {
            if ("true".equals(isStatic)) {
                return accessType + " static " + objectName + " " + attributeName;
            } else {
                return accessType + " " + objectName + " " + attributeName;
            }
        } else {
            if ("true".equals(isStatic)) {
                return accessType + " static " + attributeType + " " + attributeName;
            } else {
                return accessType + " " + attributeType + " " + attributeName;
            }
        } */


        // if("".equals(isFinal)){
        //     if("static".equals(isStatic)){
        //         if ("object".equals(attributeType)) {
        //             return accessType + "static " +objectName + " " + attributeName;
        //         } else {
        //             return accessType + " static " + attributeType + " " + attributeName;
        //         } 
        //     }
        //     else{
        //         if ("object".equals(attributeType)) {
        //             return accessType + " " + objectName + " " + attributeName;
        //         }else {
        //             return accessType + " " + attributeType + " " + attributeName;  
        //         }
        //     }
        // }

        // else{
        //     if("".equals(isStatic)){
        //         if ("object".equals(attributeType)) {
        //             return accessType +" final " +objectName + " " + attributeName;
        //         } else {
        //             return accessType + " final " + attributeType + " " + attributeName;
        //         } 
        //     }      
        // }

        
        // return accessType;
    }

    //accessors
    public String getAccessType() {
        return accessType;
    }

    public String getIsStatic() {
        return isStatic;
    }

    public String getIsFinal() {
        return isFinal;
    }

    public String getAttributeType() {
        return attributeType;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getObjectName() {
        return objectName;
    }

}
