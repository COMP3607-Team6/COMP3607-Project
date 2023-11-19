package com.example;

import java.util.ArrayList;


public interface PDFReport {

    public void update(ArrayList<TestCase> cases, String StudentID, AssignmentSpecification spec, boolean assignmentsEnd );
    // public void generatePDF(String textFilePath, String outputPDFPath) throws IOException;
    // public void writeAssertionResultsToFile(String filePath);
    // public String getAssertionResults();
    // public void clear();


    
}
    
    

  