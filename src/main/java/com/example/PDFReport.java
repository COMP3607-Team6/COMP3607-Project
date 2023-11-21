package com.example;

import java.util.ArrayList;


public interface PDFReport {


    public void update(ArrayList<TestCase> cases, String StudentID, String name, boolean assignmentsEnd, String submission_location );
    // public void generatePDF(String textFilePath, String outputPDFPath) throws IOException;
    // public void writeAssertionResultsToFile(String filePath);
    // public String getAssertionResults();
    // public void clear();


    
}
    
    

  