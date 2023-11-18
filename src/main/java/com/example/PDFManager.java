package com.example;

import java.util.ArrayList;

public class PDFManager {
    
    private ArrayList <PDFReport> observers;
    
    
    public PDFManager(){
        observers = new ArrayList<>();
        subscribe(new StudentReport());
        subscribe(new ClassReport());
    }

    public void notify (ArrayList<TestCase> cases, String StudentID, AssignmentSpecification spec){

        for(PDFReport p : observers){
            p.update(cases, StudentID, spec);
        }

        //calls update();
        
    }

    public void subscribe (PDFReport observer){
        this.observers.add(observer);
    }



    public void unsubscribe(PDFReport observer){
        this.observers.remove(observer);
    }

}
