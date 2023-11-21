package com.example;

import java.util.ArrayList;

public class PDFManager {
    
    private ArrayList <PDFReport> observers;
    private boolean assignmentsEnd;
    protected AssignmentSpecification spec;
    
    
    public PDFManager(AssignmentSpecification aSpec){
        observers = new ArrayList<>();
        assignmentsEnd = false;
        this.spec = aSpec;
        subscribe(new ClassReport(spec));
        subscribe(new StudentReport(spec));
    }

    public void notify (ArrayList<TestCase> cases, String StudentID, String submission_location){

        for(PDFReport p : observers){
            p.update(cases, StudentID, assignmentsEnd, submission_location);
        }

        //calls update();
        
    }

    public void subscribe (PDFReport observer){
        this.observers.add(observer);
    }



    public void unsubscribe(PDFReport observer){
        this.observers.remove(observer);
    }

    public void endOfAssignmentCheck(ArrayList<TestCase> cases, boolean check, String submission_location){
        this.assignmentsEnd = check;
        notify(cases,null, submission_location);
    }
 
}
