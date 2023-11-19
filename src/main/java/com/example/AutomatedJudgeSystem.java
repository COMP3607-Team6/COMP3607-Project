package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
//import java.rmi.server.Operation;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

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
    
    // //Contains all tests to be executed for the assignment
    // private static ArrayList<TestCase> testCases = new ArrayList<>();

    //No longer used and should be removed eventually
    private static StringBuilder assertionResults = new StringBuilder();
    
    //Instance of PDFManager to activate PDF observer
    private static PDFManager pdfManager = new PDFManager();

    // Demo of Assignment Specification, data suppposed to be entered from frontend
    private static AssignmentSpecification specs = new AssignmentSpecification("COMP 3607", "Assignment 1", "Mango", "folderpathwhereever", "04/11.23",5);


    
    private static ArrayList<ClassInformation> classes = new ArrayList<ClassInformation>();
    private static AssignmentSpecPortal assignmentSpecPortal;

    

    public static void main (String[] args) throws IOException{
        // initializeAssignmentSpecPortal(new AutomatedJudgeSystem(), classes);
        int num = 1;

        ArrayList<TestCase> testCases = new ArrayList<>();
String zipFilePath = "ZipFolder.zip";
// Create a File object from the zip file path
        File zipFile = new File(zipFilePath);
        ZipComponent zipComponent = null;
        ZipFileComposite zipFileComposite = null;
        try 
        {
            // Create a ZipFileComposite object from the File object
            //Adds all student assignments in student files
            zipComponent = new ZipFileComposite(zipFile); 
            zipFileComposite = (ZipFileComposite) zipComponent;
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
                        // System.out.println("HIHIHIHIHIHIHI");
                        // ZipFileReader.deleteFilesInFolder(outputFolder); This makes only STandingFan show
                        // ZipFileReader.deleteSubFolders(outputFolder);
                        if (i instanceof ZipEntryLeaf)
                        {
                            ZipEntryLeaf f = (ZipEntryLeaf)i;
                            

                            String entryName = f.getPath();

                            if (entryName.endsWith(".java"))
                            {
                                Path sourcePath = Paths.get(entryName);
                                
                                int index = f.get_rel_path().lastIndexOf("\\");
                                // Get the substring after the last \ character
                                String output = f.get_rel_path().substring(index + 1);   
                                File destinationFile = new File("src\\main\\java\\com\\example\\StudentFile\\" + output);  
                                // destinationFile.deleteOnExit();    
                                String studentJavaFileName = "src\\main\\java\\com\\example\\StudentFile\\" + output;
                                Path filePath = Paths.get(studentJavaFileName);
                                Files.createDirectories(filePath.getParent());
                            try {
                            // Create the file using the Files.createFile (Path) method
                                if (!Files.exists(filePath))
                                {
                                    Files.createFile(filePath);
                                }

                                try (BufferedWriter writer = Files.newBufferedWriter(destinationFile.toPath(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                                        // Add the package declaration to the beginning of the file

                                        Path relativePath = sourcePath.relativize(filePath);
            

            
                                        String packageName = relativePath.toString().replace("/", ".");
                                        packageName = relativePath.toString().replace("\\", ".");
                                        // Remove the file extension from the package name
                                        packageName = packageName.substring(0, packageName.lastIndexOf("."));
                                        packageName = packageName.substring(0, packageName.lastIndexOf("."));
                                        // Write the package name to the file
                                        packageName = packageName.replaceAll("^\\.+|", "");
        
                                        writer.write("package com.example." + packageName + ";");
            
            
                                        writer.newLine();
            
                                        // Copy the contents of the Java file
                                        byte[] buffer = new byte[1024];
                                        int len;

                                        FileInputStream zis = new FileInputStream(entryName);
                                        while ((len = zis.read(buffer)) > 0) {
                                            writer.write(new String(buffer, 0, len));
                                        }
                                        zis.close();
                                        writer.close();
                                    }
            
                                // Print a success message
                                // System.out.println("File created successfully: " + filePath);
                            } catch (IOException e) {
                            // Handle the exception
                               e.printStackTrace();
                            }
                                
                                
                            }


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
                testCases.add(new ClassBasicTest(1,"CeilingFan","name"));
                testCases.add(new MethodBasicTest(2,"CeilingFan","toString","name"));
                testCases.add(new AttributeBasicTest(3,"Room","devices","name"));
                testCases.add(new SubClassTest("StandingFan", "Fan",1)); //4
                testCases.add(new SubTypeTest("AC", "Device", 1)); //5
                testCases.add(new MethodTypeTest(1, "AC", "coolsBy", int.class ));
                ArrayList<Object> paras = new ArrayList<>();
                // paras.add(5);
                // paras.add("{}");
                testCases.add(new MethodValueTest("coolsBy", "AC",1, paras, 5)); //7

                

                System.out.println("HIHI");
                //runs all the tests that are added to testcases array
                executeAssignmentTest(testCases);
                pdfManager.notify(testCases, "816029005" + num, specs);
                num++;
                  System.out.println(num);
                
                  
                  testCases.clear();
                //   paras.clear();
                  
                
                  try {
                    Delete.deleteFilesInFolder(outputFolder);
                  }
                  catch (Exception e)
                  {
                    e.printStackTrace();
                  }

                 
                  
            } //End of student for loop
            
        }
        catch (IOException e) {
            // Handle the exception
            System.out.println("Unable to read folder. " + e.getMessage());
        }

        
        

        

        pdfManager.notify(testCases, "816029005", specs);
        pdfManager.notify(testCases, "816029002", specs);
        pdfManager.notify(testCases, "816029007", specs);


        pdfManager.endOfAssignmentCheck(testCases,specs,true);
           
      
        // for (ZipComponent a : zipFileComposite.getComponents())
        // {
        //     // a.printInfo();
        // }

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





    ////

    public void initializeAssignmentSpecPortal() {
        assignmentSpecPortal = new AssignmentSpecPortal(this, classes);
    }

    public static void initializeAssignmentSpecPortal(AutomatedJudgeSystem instance, ArrayList<ClassInformation> classes) {
        assignmentSpecPortal = new AssignmentSpecPortal(instance, classes);
    }

    public static void onGUIRunTestsButtonPressed() {
        System.out.println("Run tests button in section 5 pressed; AJS notified.");
        System.out.println("Num of test cases: " + TestCaseManager.getTestCases().size());
        System.out.println("Test cases: ");
        System.out.println(TestCaseManager.getTestCases());
    }

    
    // method which calls helper methods to execute the whole process of marking a student assignment
    // public static void runTest(String studentId, AssignmentSpecification specs){

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

   

}