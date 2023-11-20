package com.example.HierarchyTests;

import com.example.TestCase;

public abstract class HierarchyTest extends TestCase {

    public HierarchyTest(int allocatedMarks)
    {
        super(allocatedMarks);
    }

    public Class<?> findExtensibleSuperClass(String superClass){

        Class<?> superClassObj = findClassInstance(superClass,allAbstractClasses);

        if (superClassObj == null){
            superClassObj =  findClassInstance(superClass, allConcreteClasses);
        }

        return superClassObj;
    }

}
