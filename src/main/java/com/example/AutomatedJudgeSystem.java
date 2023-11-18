package com.example;

import java.io.IOException;
import java.rmi.server.Operation;
import java.util.ArrayList;

import com.example.AssignmentSpecificationPortal.AssignmentSpecPortal;
import com.example.AssignmentSpecificationPortal.ClassInformation;
import com.example.BasicTest.AttributeBasicTest;
import com.example.BasicTest.ClassBasicTest;
import com.example.BasicTest.MethodBasicTest;
import com.example.BehaviourTests.MethodTypeTest;
import com.example.BehaviourTests.MethodValueTest;
import com.example.HierarchyTests.SubClassTest;
import com.example.HierarchyTests.SubTypeTest;

public class AutomatedJudgeSystem {
    
    //Contains all tests to be executed for the assignment
    private static ArrayList<TestCase> testCases = new ArrayList<>();

    //No longer used and should be removed eventually
    private static StringBuilder assertionResults = new StringBuilder();
    
    //Instance of PDFManager to activate PDF observer
    private static PDFManager pdfManager = new PDFManager();

    // Demo of Assignment Specification, data suppposed to be entered from frontend
    private static AssignmentSpecification specs = new AssignmentSpecification("COMP 3607", "Assignment 1", "Mango", "folderpathwhereever", "04/11.23",5);
    private static AssignmentSpecPortal assignmentSpecPortal;

    

    public static void main (String[] args) throws IOException{
        initializeAssignmentSpecPortal(new AutomatedJudgeSystem());

        testCases.add(new ClassBasicTest(1,"CeilingFan","name"));
        testCases.add(new MethodBasicTest(2,"CeilingFan","toString","name"));
        testCases.add(new AttributeBasicTest(3,"Room","devices","name"));
        testCases.add(new SubClassTest("StandingFan", "Fan",1));
        testCases.add(new SubTypeTest("AC", "Device",1));
        testCases.add(new MethodTypeTest(1, "AC", "coolzBy", int.class ));
        ArrayList<Object> paras = new ArrayList<>();
        paras.add(5);
        paras.add("{}");
        testCases.add(new MethodValueTest("coolsBy", "AC",1, paras, 13));
        

        //runs all the tests that are added to testcases array
        executeAssignmentTest();

        pdfManager.notify(testCases, "816029005", specs);
        pdfManager.notify(testCases, "816029002", specs);
        pdfManager.notify(testCases, "816029007", specs);


        pdfManager.endOfAssignmentCheck(testCases,specs,true);

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

    
    // the following 2 initializeAssignmentSpecPortal methods were made to get around error: Cannot use this in a static context
    public void initializeAssignmentSpecPortal() {
        assignmentSpecPortal = new AssignmentSpecPortal(this);
    }

    public static void initializeAssignmentSpecPortal(AutomatedJudgeSystem instance) {
        assignmentSpecPortal = new AssignmentSpecPortal(instance);
    }

    // on the last frame of the gui, there is a run tests button. when that is pressed it calls back to this method
    // TestCaseManager stores an arraylist of test cases and some methods to add and remove from the arraylist
    public static void onGUIRunTestsButtonPressed() {
        System.out.println("Run tests button in section 5 pressed; AJS notified.");
        System.out.println("Num of test cases: " + TestCaseManager.getTestCases().size());
        System.out.println("Test cases: ");
        System.out.println(TestCaseManager.getTestCases());
    }
}