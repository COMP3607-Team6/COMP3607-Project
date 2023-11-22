package com.example.AssignmentSpecificationPortal;

/**
 * This class holds all the information of saved Attributes in the GUI
 */
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
