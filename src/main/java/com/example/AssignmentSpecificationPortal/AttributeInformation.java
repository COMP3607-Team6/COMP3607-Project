package com.example.AssignmentSpecificationPortal;

public class AttributeInformation {
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
        if("false".equals(isFinal)){
            if("true".equals(isStatic)){
                if ("object".equals(attributeType)) {
                    return accessType +" " +objectName + " " + attributeName;
                } else {
                    return accessType + " " + attributeType + " " + attributeName;
                } 
            }
            else{
                if ("object".equals(attributeType)) {
                    return accessType + " static " + objectName + " " + attributeName;
                }else {
                    return accessType + " static " + attributeType + " " + attributeName;  
                }
            }
        }

        else{
            if("true".equals(isStatic)){
                if ("object".equals(attributeType)) {
                    return accessType +" final " +objectName + " " + attributeName;
                } else {
                    return accessType + " final " + attributeType + " " + attributeName;
                } 
            }
            else{
                if ("object".equals(attributeType)) {
                    return accessType + " final static " + objectName + " " + attributeName;
                }else {
                    return accessType + "  final static " + attributeType + " " + attributeName;  
                }
            }
        }
    }

    public String getAccessType() {
        return accessType;
    }

    public String getIsStatic() {
        return isStatic;
    }

    public String getIsFInal() {
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
