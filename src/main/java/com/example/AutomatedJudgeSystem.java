package com.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Enumeration;

import com.example.AssignmentSpecificationPortal.AssignmentSpecPortal;
import com.example.BasicTest.AttributeBasicTest;
import com.example.BasicTest.ClassBasicTest;
import com.example.BasicTest.MethodBasicTest;
import com.example.BehaviourTests.MethodTypeTest;
import com.example.BehaviourTests.MethodValueTest;
import com.example.FileCopy.JavaFileCopier;
import com.example.FileCopy.SubmissionCopier;
import com.example.FileCopy.ZipToFolderCopier;
import com.example.ZipFileEntries.ZipComponent;
import com.example.ZipFileEntries.ZipDirectory;
import com.example.ZipFileEntries.ZipEntryLeaf;
import com.example.ZipFileEntries.ZipFileComposite;
import com.example.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AutomatedJudgeSystem {
    
    // //Contains all tests to be executed for the assignment
    // private static ArrayList<TestCase> testCases = new ArrayList<>();

    //No longer used and should be removed eventually
    private static StringBuilder assertionResults = new StringBuilder();
    
    //Instance of PDFManager to activate PDF observer
    private static PDFManager pdfManager;

    // Demo of Assignment Specification, data suppposed to be entered from frontend
    private static AssignmentSpecification asSpec = new AssignmentSpecification("", "", "", "", "",0);
    private static AssignmentSpecPortal assignmentSpecPortal;

    

    public static void main (String[] args) throws IOException{

        Path path = Paths.get(Constants.GRADED_SUBMISSIONS);

        try {
            Files.createDirectories(path);
            System.out.println("Directory is created!");
        } catch (IOException e) {
            System.err.println("Failed to create directory!" + e.getMessage());
        }

        Path path2 = Paths.get(Constants.STUDENT_SUBMISSION_TESTING_FOLDER);

        try {
            Files.createDirectories(path2);
            System.out.println("Directory is created!");
        } catch (IOException e) {
            System.err.println("Failed to create directory!" + e.getMessage());
        }

        initializeAssignmentSpecPortal(new AutomatedJudgeSystem(), asSpec);


    }

    public static void doTest () throws IOException{
        
        Delete.deleteFolder("GradedSubmissions.zip");
        System.out.println("zipFilePathugui");

        int num = 0;

        pdfManager = new PDFManager(asSpec);

        ArrayList<TestCase> testCases = TestCaseManager.getTestCases();
        ArrayList <String> assignmentNames = new ArrayList<>();
        ArrayList <String> studentIds = new ArrayList<>();

        String zipFilePath = asSpec.getFolderPath();
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
            assignmentNames = ((ZipFileComposite) zipComponent).getFileNames();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return;
        }

      
        try {
      
            // Iterate student submissions
            SystemNotification testExecutionNotification = new SystemNotification("Test Suite is processing assignments :)");

            for (ZipComponent z : zipFileComposite.getComponents()) 
            {
               String outputFolder = Constants.STUDENT_SUBMISSION_TESTING_FOLDER;
               ZipFileComposite c = (ZipFileComposite)z;
               Path submission_location = SubmissionCopier.copySubmission(z); // Adds the student submission to the StudentFile folder to put PDF report
           
                //Iterate student files
                for (ZipComponent i : c.getComponents())
                    {
                        if (i instanceof ZipEntryLeaf)
                        {
                            ZipEntryLeaf f = (ZipEntryLeaf)i;

                            JavaFileCopier.javaFileCopierToLeaf (f.getPath(), f);
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
            

                for(TestCase t: testCases){
                    t.init();
                }

                //runs all the tests that are added to testcases array
                executeAssignmentTest(testCases);
                pdfManager.notify(testCases, studentIds.get(num), submission_location.toString());
                num++;
                
                for(TestCase t: testCases){
                    t.reset();
                }
                  
                
                  try {
                    ZipToFolderCopier.copyFile(submission_location, Constants.GRADED_SUBMISSIONS);
                    
                    //
                    try {
                        // Pause for 5 seconds
                        Thread.sleep (5000);
                        } catch (Exception e) {
                            // Handle the interruption
                            e.printStackTrace ();
                        }
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

        ZipDirectory.zipDirectory(Constants.GRADED_SUBMISSIONS, Constants.GRADED_SUBMISSIONS_ZIP);

         try {
                // Pause for 5 seconds
                Thread.sleep (5000);
        } 
        catch (Exception e) 
        {
                // Handle the interruption
                e.printStackTrace ();
        }

        pdfManager.endOfAssignmentCheck(testCases,true, "src\\main\\java\\com\\example\\tobeDeleted\\FancyTable.pdf");

        zipFileComposite.removeAll();

       Delete.deleteFolder(new File(Constants.STUDENT_SUBMISSIONS_FOLDER));

        try {
                // Pause for 5 seconds
                Thread.sleep (5000);
        } 
        catch (Exception e) 
        {
                // Handle the interruption
                e.printStackTrace ();
        }

        
        Delete.deleteFilesInFolder(Constants.STUDENT_SUBMISSION_TESTING_FOLDER);
        Delete.deleteFolder(Constants.GRADED_SUBMISSIONS);


        
        
        
    }

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

    public static void onGUIFolderButtonPressed() {
       
       SystemNotification e = new SystemNotification();
       e.openFolderInExplorer("GradedSubmissions.zip");
    }
}