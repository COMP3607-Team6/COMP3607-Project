package com.example;

import java.io.File;

public class App 
{
   
    public static void main( String[] args )
    {
        // System.out.println( "Hello World!!!!!! :)))))" );

        String dirPath = "src\\main\\java\\com\\example\\TestAssignment"; // replace with your directory

        JavaFileCopier.copyFiles(dirPath);

        try {
            // Pause for 5 seconds
            Thread.sleep (5000);
        } catch (InterruptedException e) {
            // Handle the interruption
            e.printStackTrace ();
        }

        try{
        
            FileToZipCopier.copyFile(new File ("src\\main\\java\\com\\example\\assignment.zip"), new File ("1233456.pdf"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Delete.deleteFilesInFolder("src\\main\\java\\com\\example\\StudentFile");
        // Delete.deleteFolder("src\\main\\java\\com\\example\\StudentFiles");

    }

    
}
    









