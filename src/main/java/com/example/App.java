package com.example;

import java.io.File;

public class App 
{
   
    public static void main( String[] args )
    {
        // System.out.println( "Hello World!!!!!! :)))))" );

        // String dirPath = "src\\main\\java\\com\\example\\TestAssignment"; // replace with your directory

        // JavaFileCopier.copyFiles(dirPath);

        // try {
        //     // Pause for 5 seconds
        //     Thread.sleep (5000);
        // } catch (InterruptedException e) {
        //     // Handle the interruption
        //     e.printStackTrace ();
        // }

        // try{
        
        //     FileToZipCopier.copyFile(new File ("src\\main\\java\\com\\example\\assignment.zip"), new File ("1233456.pdf"));
        // }
        // catch (Exception e)
        // {
        //     e.printStackTrace();
        // }

        // Delete.deleteFilesInFolder("src\\main\\java\\com\\example\\StudentFile");
        // Delete.deleteFolder("src\\main\\java\\com\\example\\StudentFiles");

        System.out.println(getNameFromSubmission("ZipFolder/name_345678_comp.zip", "comp"));

    }

    public static String getNameFromSubmission(String str, String assignmentName) {
        // Remove everything before '/'
        int index = str.lastIndexOf('/');
        if (index != -1) {
            str = str.substring(index + 1);
        }

        // Remove any numbers, '_', and 'assignment_name'
        str = str.replaceAll("(?i)[0-9_]*" + assignmentName, "");

        // Remove the file extension
        index = str.lastIndexOf('.');
        if (index != -1) {
            str = str.substring(0, index);
        }

        return str;
    }
    
}
    









