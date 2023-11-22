package com.example;

import java.util.ArrayList;

/**
 * Observer interface for PDF concrete Observer classes
*/
public interface PDFReport {

    public void update(ArrayList<TestCase> cases, String StudentID, String name, boolean assignmentsEnd, String submission_location );



}
    
    

  