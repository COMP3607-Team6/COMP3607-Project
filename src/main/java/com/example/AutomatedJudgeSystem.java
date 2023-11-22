package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.AssignmentSpecificationPortal.AssignmentSpecPortal;
import com.example.FileCopy.JavaFileCopier;
import com.example.FileCopy.SubmissionCopier;
import com.example.FileCopy.ZipToFolderCopier;
import com.example.ZipFileEntries.ZipComponent;
import com.example.ZipFileEntries.ZipDirectory;
import com.example.ZipFileEntries.ZipEntryLeaf;
import com.example.ZipFileEntries.ZipFileComposite;

/**
 * Handles the main process of the application
*/
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

        Path path = Paths.get("src\\main\\java\\com\\example\\GradedSubmissions");

        try {
            Files.createDirectories(path);
            System.out.println("Directory is created!");
        } catch (IOException e) {
            System.err.println("Failed to create directory!" + e.getMessage());
        }

        Path path2 = Paths.get("src\\main\\java\\com\\example\\StudentFile");

        try {
            Files.createDirectories(path2);
            System.out.println("Directory is created!");
        } catch (IOException e) {
            System.err.println("Failed to create directory!" + e.getMessage());
        }

        Path path3 = Paths.get("src\\main\\java\\com\\example\\UngradedSubmissions");

        try {
            Files.createDirectories(path3);
            System.out.println("Directory is created!");
        } catch (IOException e) {
            System.err.println("Failed to create directory!" + e.getMessage());
        }


        initializeAssignmentSpecPortal(new AutomatedJudgeSystem(), asSpec);


    }

    public static void doTest () throws IOException{
        
        Delete.deleteFolder("GradedSubmissions.zip");
        Delete.deleteFolder("UngradedSubmissions.zip");
        System.out.println("zipFilePathugui");


        int num = 0;

        pdfManager = new PDFManager(asSpec);

        ArrayList<TestCase> testCases = TestCaseManager.getTestCases();
        ArrayList <String> assignmentNames = new ArrayList<>();
        Map<String, String> result = new HashMap<>();
        // ArrayList <String> studentIds = new ArrayList<>();

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
               String outputFolder = "src\\main\\java\\com\\example\\StudentFile";
               ZipFileComposite c = (ZipFileComposite)z;
               Path submission_location = SubmissionCopier.copySubmission(z); // Adds the student submission to the StudentFile folder to put PDF report
            //    Delete.deleteFolder(c.getPath());

                result = getNameFromSubmission(assignmentNames.get(num), "comp");
               
                
                String name = result.get("name");
                String number = result.get("number");

                if(name.length() == 0 || number.length() == 0){
                    ZipToFolderCopier.copyFile(Paths.get(z.getPath()), "src\\main\\java\\com\\example\\UngradedSubmissions\\");
                    System.out.println("Bad Submission Added!!");
                    break;
                }
                num++;
           
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

                for(TestCase t: testCases){
                    t.init();
                }

                //runs all the tests that are added to testcases array
                executeAssignmentTest(testCases);
                pdfManager.notify(testCases, result.get("number"), result.get("name"), submission_location.toString());
                result.clear();
                
                for(TestCase t: testCases){
                    t.reset();
                }
                  
                
                  try {
                    // copyFile(submission_location.toString(), "src\\main\\java\\com\\example\\GradedSubmissions\\");
                    String destination = "src\\main\\java\\com\\example\\GradedSubmissions\\";
                    ZipToFolderCopier.copyFile(submission_location, destination);
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

      
        
          try {
                // Pause for 5 seconds
                Thread.sleep (5000);
        } 
        catch (Exception e) 
        {
                // Handle the interruption
                e.printStackTrace ();
        }

        ZipDirectory.zipDirectory("src\\main\\java\\com\\example\\GradedSubmissions", "GradedSubmissions.zip");

          try {
                // Pause for 5 seconds
                Thread.sleep (5000);
        } 
        catch (Exception e) 
        {
                // Handle the interruption
                e.printStackTrace ();
        }

        ZipDirectory.zipDirectory("src\\main\\java\\com\\example\\UngradedSubmissions", "UngradedSubmissions.zip");
         try {
                // Pause for 5 seconds
                Thread.sleep (5000);
        } 
        catch (Exception e) 
        {
                // Handle the interruption
                e.printStackTrace ();
        }

          pdfManager.endOfAssignmentCheck(testCases,true, "GradedSubmissions.zip");


        zipFileComposite.removeAll();

       Delete.deleteFolder(new File("src\\main\\java\\com\\example\\StudentFiles"));

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
        Delete.deleteFilesInFolder("src\\main\\java\\com\\example\\UngradedSubmissions");

        
        
        
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
        // System.out.println("Run tests button in section 5 pressed; AJS notified.");
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


    public static void onGUIUnGradedFolderButtonPressed() {
       
       SystemNotification e = new SystemNotification();
       e.openFolderInExplorer("UngradedSubmissions.zip");
    }

      public static Map<String,String> getNameFromSubmission(String str, String assignmentName) {
       // Remove everything before the last '/'
            int index = str.lastIndexOf('/');
            if (index != -1) {
                str = str.substring(index + 1);
            }

            // Remove the assignment name
            str = str.replaceAll("(?i)" + assignmentName, "");

            // Remove any 'A' followed by a digit
            str = str.replaceAll("A\\d", "");

            // Remove the file extension
            index = str.lastIndexOf('.');
            if (index != -1) {
                str = str.substring(0, index);
            }

            // Split the string by '_'
            String[] parts = str.split("_");

            // Initialize name and number
            String name = "";
            String number = "";

            for (String part : parts) {
                // If part is numeric and has 6 or more digits, it's the number
                if (part.matches("\\d{6,}")) {
                    number = part;
                }
                // Otherwise, if it's not the assignment name, it's part of the name
                else if (!part.equalsIgnoreCase(assignmentName)) {
                    name += part + " ";
                }
            }

            name = str.replaceAll("(?i)[0-9_]*", "");

            // Create a map and put the name and number into it
            Map<String, String> result = new HashMap<>();
            result.put("name", name);
            result.put("number", number);

            return result;
            
}


}