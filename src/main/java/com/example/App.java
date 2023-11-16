package com.example;

import java.io.File;
import java.io.IOException;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!!!!!! :)))))" );
        // ZipFileInput z = new ZipFileInput("Enter zip file path here");
        // z.readFiles();

//         AssignmentSpecPortal assignmentSpecPortal = new AssignmentSpecPortal();
//         assignmentSpecPortal.setVisible(true);

       // The path of the outer zip file
    String zipFilePath = "ZipFolder.zip";
    // Create a File object from the zip file path
    File zipFile = new File(zipFilePath);
    try {
        // Create a ZipFileComposite object from the File object
        ZipComponent zipComponent = new ZipFileComposite(zipFile);
        // Print the info of the zip component
        // zipComponent.printInfo();
        // // Cast the zipComponent object to a ZipFileComposite object
        ZipFileComposite zipFileComposite = (ZipFileComposite) zipComponent;
        // Iterate over the child components of the zip file composite object
        for (ZipComponent z : zipFileComposite.getComponents()) {
            // Print the info of each component
// System.out.println(z.getClass());
            z.printInfo();
        }

    } catch (IOException e) {
        // Handle the exception
        e.printStackTrace();
    }
    }
}
