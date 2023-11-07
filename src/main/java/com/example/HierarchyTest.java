package com.example;

import java.util.ArrayList;

public abstract class HierarchyTest implements TestCase {
    private Instances instances;
    private ArrayList<Object> testResult;

    public HierarchyTest()
    {
        this.instances = new Instances();
        testResult = this.instances.getInstances();
    }

    public Object findClassInstance(String name){

        for(Object o : testResult){
            String className = o.getClass().getSimpleName();

            if(className.equals(name)){
                return o;
            }
        }

        return null;
    }
}
