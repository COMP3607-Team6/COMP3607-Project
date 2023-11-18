package com.example;

import java.io.IOException;


import java.util.ArrayList;

import com.example.BasicTest.AttributeBasicTest;
import com.example.BasicTest.ClassBasicTest;
import com.example.BasicTest.MethodBasicTest;

public class AutomatedJudgeSystem {
    
    //Contains all tests to be executed for the assignment
    private static ArrayList<TestCase> testCases = new ArrayList<>();

    //No longer used and should be removed eventually
    private static StringBuilder assertionResults = new StringBuilder();
    
    //Instance of PDFManager to activate PDF observer
    private static PDFManager pdfManager = new PDFManager();

    // Demo of Assignment Specification, data suppposed to be entered from frontend
    private static AssignmentSpecification specs = new AssignmentSpecification("COMP 3607", "Assignment 1", "Mango", "folderpathwhereever", "04/11.23",5);


    

    public static void main (String[] args) throws IOException{
        

        testCases.add(new ClassBasicTest(1,"CeilingFan","name"));
        testCases.add(new MethodBasicTest(2,"CeilingFan","toString","name"));
        testCases.add(new AttributeBasicTest(3,"Room","devices","name"));

        //runs all the tests that are added to testcases array
        executeAssignmentTest();

        pdfManager.notify(testCases, "816029005", specs);
        pdfManager.notify(testCases, "816029002", specs);
        pdfManager.notify(testCases, "816029007", specs);

    }

     // method which calls helper methods to execute the whole process of marking a student assignment
    public static void processAssignment(ArrayList<TestCase> testCases, String studentId, AssignmentSpecification specs){

        // 1) some method to read the data from the frontend and create all tests for it based on the spec

        //LOOP for every Assignment:

            //2) method to retrieve student zip file from folder and unzip that assignment
                //Retrieve studentID and possibly Name??
                // If file not following naming convention then A flagged folder is generated where that report is saved??
            
            //3) some method to run the test and generate associated comments for each test
                executeAssignmentTest();

            //4) method to generate pdf output for a student assignment
                pdfManager.notify(testCases, studentId, specs);

    }


    public static void executeAssignmentTest(){

        for(TestCase test: testCases){
            String assertionResultString = test.test();
         }
    }

   

}