package com.example.AssignmentSpecificationPortal;

public class ClassInformation {
    private final String accessType;
    private final String isAbstract;
    private final String isInterface;
    private final String className;
    private final String extendsOrImplements;
    private final String extendedOrImplementedClass;
    private final String marks;

    public ClassInformation(String accessType, String isAbstract, 
                            String isInterface, String className,
                            String extendsOrImplements, 
                            String extendedOrImplementedClass, 
                            String marks) {
        this.accessType = accessType;
        this.isAbstract = isAbstract;
        this.isInterface = isInterface;
        this.className = className;
        this.extendsOrImplements = extendsOrImplements;
        this.extendedOrImplementedClass = extendedOrImplementedClass;
        this.marks = marks;
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