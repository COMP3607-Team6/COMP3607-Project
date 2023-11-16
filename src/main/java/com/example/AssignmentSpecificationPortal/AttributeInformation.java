package com.example.AssignmentSpecificationPortal;

public class AttributeInformation {
    private final String accessType;
    private final String isStatic;
    private final String attributeType;
    private final String attributeName;
    private final String objectName;

    public AttributeInformation(String accessType, String isStatic, String attributeType,
                                String attributeName, String objectName) {
        this.accessType = accessType;
        this.isStatic = isStatic;
        this.attributeType = attributeType;
        this.attributeName = attributeName;
        this.objectName = objectName;
    }

    @Override
    public String toString() {
        if ("object".equals(attributeType)) {
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
        }
    }

    public String getAccessType() {
        return accessType;
    }

    public String getIsStatic() {
        return isStatic;
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
