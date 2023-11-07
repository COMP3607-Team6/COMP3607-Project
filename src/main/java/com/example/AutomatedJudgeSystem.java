package com.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


import java.util.ArrayList;

import com.example.NamingConventionTests.AttributeNameTest;
import com.example.NamingConventionTests.ClassNameTest;
import com.example.NamingConventionTests.MethodNameTest;

public class AutomatedJudgeSystem {
    

    private static ArrayList<TestCase> testCases = new ArrayList<>();
    private static StringBuilder assertionResults = new StringBuilder();



    

    public static void main (String[] args) throws IOException{
      

        testCases.add(new ClassNameTest("CeilingFan"));
        testCases.add(new MethodNameTest("CeilingFan","toString"));
        testCases.add(new AttributeNameTest("Room","devices"));

         for(TestCase test: testCases){
                String assertionResultString = test.test();
                assertionResults.append("Test Case: ").append(assertionResultString).append("\n");
         }

         writeAssertionResultsToFile("src\\main\\java\\com\\example\\TestCases.txt");

         PDFReport report = new PDFReport();
         String textFilePath ="src\\main\\java\\com\\example\\TestCases.txt";
         String pdfPath = "src\\main\\java\\com\\example\\Output.pdf" ;
         report.generatePDF(textFilePath, pdfPath);

    }
   
    public static void writeAssertionResultsToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Get the assertion results and write them to the file
            String results = getAssertionResults();
            writer.write(results);
            System.out.println("Assertion results have been written to the file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getAssertionResults() {
        return assertionResults.toString();
    }


}