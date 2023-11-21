package com.example.BehaviourTests;

import java.util.HashMap;
import java.util.Map;

public abstract class TypeTest extends BehaviourTest {
    
    
    private Map<String, Class<?>> primitiveTypesMap;



    public TypeTest(int allocatedMarks){
        super(allocatedMarks);
        

    }

    public Class<?> convertKeywordToObject(String keyword){
        initMap();
        Class<?> type = primitiveTypesMap.get(keyword);
        
        if (type == null) {
            try {
                type = Class.forName("com.example.StudentFile." + keyword);
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace(); // Handle the exception accordingly
            }
        }

        System.out.println(type);
        return type;

    }

    public void initMap(){
        this.primitiveTypesMap = new HashMap<>();

        primitiveTypesMap.put("int", int.class);
        primitiveTypesMap.put("float", float.class);
        primitiveTypesMap.put("double", double.class);
        primitiveTypesMap.put("boolean", boolean.class);
        primitiveTypesMap.put("char", char.class);
        primitiveTypesMap.put("String", String.class);


    }
}
