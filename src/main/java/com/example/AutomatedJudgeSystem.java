package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import com.example.AssignmentSpecificationPortal.AssignmentSpecPortal;
import com.example.AssignmentSpecificationPortal.ClassInformation;




public class AutomatedJudgeSystem {
    
    // //Contains all tests to be executed for the assignment
    // private static ArrayList<TestCase> testCases = new ArrayList<>();

    //No longer used and should be removed eventually
    private static StringBuilder assertionResults = new StringBuilder();
    
    //Instance of PDFManager to activate PDF observer
    private static PDFManager pdfManager = new PDFManager();

    // Demo of Assignment Specification, data suppposed to be entered from frontend
    private static AssignmentSpecification asSpec = new AssignmentSpecification("COMP 3607", "Assignment 1", "Mango", "folderpathwhereever", "04/11.23",5);
    private static AssignmentSpecPortal assignmentSpecPortal;

    

    public static void main (String[] args) throws IOException{
        initializeAssignmentSpecPortal(new AutomatedJudgeSystem());
    }

    public static void doTest () throws IOException{
        
        int num = 1;

        ArrayList<TestCase> testCases = TestCaseManager.getTestCases();


        String zipFilePath = "ZipFolder.zip";
        // Create a File object from the zip file path
        File zipFile = new File(zipFilePath);
        ZipComponent zipComponent = null;
        Composite zipFileComposite = null;
        try 
        {
            // Create a ZipFileComposite object from the File object
            //Adds all student assignments in student files
            zipComponent = new ZipFileComposite(zipFile); 
            zipFileComposite = (Composite) zipComponent;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return;
        }
        
        
        try {
      
            // Iterate student submissions
            for (ZipComponent z : zipFileComposite.getComponents()) 
            {
                 
                

                // System.out.println("VYVIVUVAIUXVIUVSXIPVSCIYVSPVCUPYVWSCPYVWSCPYVWSYVCPYWVC*UVW(UGC(UW(UCV(WUVCU(W)))))");
               String outputFolder = "src\\main\\java\\com\\example\\StudentFile";
               ZipFileComposite c = (ZipFileComposite)z;
               Path submission_location = SubmissionCopier.copySubmission(z); // Adds the student submission to the StudentFile folder to put PDF report
            //    System.out.println("JIJINISIISJSIJSIJSIJIPSJSPJPSIS " + c.getPath());
               Delete.deleteFolder(c.getPath());
                

                
                //Iterate student files
                for (ZipComponent i : c.getComponents())
                    {
                        if (i instanceof ZipEntryLeaf)
                        {
                            ZipEntryLeaf f = (ZipEntryLeaf)i;
                            

                            String entryName = f.getPath();

                            JavaFileCopier.javaFileCopierToLeaf (entryName, f);


                        }
    } //End of java file iteration
                 
                //Needed so files have time to be created and recognized
                try {
                    // Pause for 5 seconds
                    Thread.sleep (3000);
                } catch (InterruptedException e) {
                    // Handle the interruption
                    e.printStackTrace ();
                }
               
                //Contains all tests to be executed for the assignment
                // testCases.add(new ClassBasicTest(1,"CeilingFan","name"));
                // testCases.add(new MethodBasicTest(2,"CeilingFan","toString","name"));
                // testCases.add(new AttributeBasicTest(3,"Room","devices","name"));
                // testCases.add(new SubClassTest("StandingFan", "Fan",1)); //4
                // testCases.add(new SubTypeTest("AC", "Device", 1)); //5
                // testCases.add(new MethodTypeTest(1, "AC", "coolsBy", int.class ));
                // ArrayList<Object> paras = new ArrayList<>();
                // // paras.add(5);
                // // paras.add("{}");
                // testCases.add(new MethodValueTest("coolsBy", "AC",1, paras, 5)); //7

                for(TestCase t: testCases){
                    t.init();
                }

                

                System.out.println("HIHI");
                //runs all the tests that are added to testcases array
                executeAssignmentTest(testCases);
                pdfManager.notify(testCases, "816029005" + num, specs);
                num++;
                  System.out.println(num);
                
                 
                 // testCases.clear();
                //   paras.clear();

                for(TestCase t: testCases){
                    t.reset();
                }
                  
                
                  try {
                    Delete.deleteFilesInFolder(outputFolder);
                  }
                  catch (Exception e)
                  {
                    e.printStackTrace();
                  }

                 
                  
            } //End of student for loop
            
        }
        catch (Exception e) {
            // Handle the exception
            System.out.println("Unable to read folder. " + e.getMessage());
        }

        pdfManager.endOfAssignmentCheck(testCases,specs,true);

        zipFileComposite.removeAll();

        for (ZipComponent a : zipFileComposite.getComponents())
        {
            a.printInfo();
        }

        Delete.deleteFolder(new File("src\\main\\java\\com\\example\\StudentFiles"));

        try {
                    // Pause for 5 seconds
                    Thread.sleep (5000);
                } catch (Exception e) {
                    // Handle the interruption
                    e.printStackTrace ();
                }

    }

    

    //  // method which calls helper methods to execute the whole process of marking a student assignment
    // public static void processAssignment(ArrayList<TestCase> testCases, String studentId, AssignmentSpecification specs){

    //     // 1) some method to read the data from the frontend and create all tests for it based on the spec

    //     //LOOP for every Assignment:

    //         //2) method to retrieve student zip file from folder and unzip that assignment
    //             //Retrieve studentID and possibly Name??
    //             // If file not following naming convention then A flagged folder is generated where that report is saved??
            
    //         //3) some method to run the test and generate associated comments for each test
    //             executeAssignmentTest();

    //         //4) method to generate pdf output for a student assignment
    //             pdfManager.notify(testCases, studentId, specs);

    // }


    public static void executeAssignmentTest(ArrayList<TestCase> testCases){

        for(TestCase test: testCases){
            String assertionResultString = test.test();
         }
    }

    
    // the following 2 initializeAssignmentSpecPortal methods were made to get around error: Cannot use this in a static context
    public void initializeAssignmentSpecPortal() {
        assignmentSpecPortal = new AssignmentSpecPortal(this, asSpec);
    }

    public static void initializeAssignmentSpecPortal(AutomatedJudgeSystem instance, AssignmentSpecification asSpec) {
        assignmentSpecPortal = new AssignmentSpecPortal(instance,asSpec);
    }

    // on the last frame of the gui, there is a run tests button. when that is pressed it calls back to this method
    // TestCaseManager stores an arraylist of test cases and some methods to add and remove from the arraylist
    public static void onGUIRunTestsButtonPressed() {
        System.out.println("Run tests button in section 5 pressed; AJS notified.");
        System.out.println("Num of test cases: " + TestCaseManager.getTestCases().size());
        System.out.println("Test cases: ");
      

        System.out.println(TestCaseManager.getTestCases());
        try {
            doTest();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //return TestCaseManager.getTestCases();
    }
}