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

        System.out.println(getNameFromSubmission("816029001_Avinash_Roopnarine_Comp_2603_A2.zip", "comp"));
        System.out.println(getNameFromSubmission("ZipFolder/name_345678_comp.zip", "comp"));

    }

    public static String getNameFromSubmission(String str, String assignmentName) {
        // Remove everything before '/'
        int index = str.lastIndexOf('/');
        if (index != -1) {
            str = str.substring(index + 1);
        }


        str = str.replaceAll("_A\\d", "_");

        // Remove the file extension
        index = str.lastIndexOf('.');
        if (index != -1) {
            str = str.substring(0, index);
        }

        // Split the string by '_'
        String[] parts = str.split("_");

        // Concatenate the parts of the name
        String name = "";
        for (String part : parts) {
            if (!part.matches("\\d+") && !part.equalsIgnoreCase(assignmentName)) {
                name += part;
            }
        }

        return name;
    }
    
}
    









