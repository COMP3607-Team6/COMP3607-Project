package com.example;

import java.util.ArrayList;

public class PDFManager {
    
    private ArrayList <PDFReport> observers;
    private boolean assignmentsEnd;
    
    
    public PDFManager(){
        observers = new ArrayList<>();
        assignmentsEnd = false;
        subscribe(new TestPDF());
        subscribe(new TestPDF1());
    }

    public void notify (ArrayList<TestCase> cases, String StudentID, AssignmentSpecification spec){

        for(PDFReport p : observers){
            p.update(cases, StudentID, spec,assignmentsEnd);
        }

        //calls update();
        
    }

    public void subscribe (PDFReport observer){
        this.observers.add(observer);
    }



    public void unsubscribe(PDFReport observer){
        this.observers.remove(observer);
    }

    public void endOfAssignmentCheck(ArrayList<TestCase> cases, AssignmentSpecification spec, boolean check){
        this.assignmentsEnd = check;
        notify(cases,null,spec);
    }

}
