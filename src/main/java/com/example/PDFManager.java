package com.example;

import java.util.ArrayList;

/**
 * Helper Object for Observer Pattern, Manages the updates of PDFs (Observers)
*/

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

    /**
    *  Informs PDFs of Updates
    */
    public void notify (ArrayList<TestCase> cases, String StudentID, String name, String submission_location){


        for(PDFReport p : observers){
            p.update(cases, StudentID, name,  assignmentsEnd, submission_location);
        }

        //calls update();
        
    }

    public void subscribe (PDFReport observer){
        this.observers.add(observer);
    }



    public void unsubscribe(PDFReport observer){
        this.observers.remove(observer);
    }

    /**
    * Signals to PDF observers that all Assignment files are processed
    */
    public void endOfAssignmentCheck(ArrayList<TestCase> cases, boolean check, String submission_location){
        this.assignmentsEnd = check;
        notify(cases,null, null,submission_location);
    }
 
}
