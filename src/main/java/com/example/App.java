package com.example;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.example.AssignmentSpecificationPortal.AssignmentSpecPortal;
import com.example.AssignmentSpecificationPortal.ClassInformation;


public class App 
{
    
    public static void main( String[] args )
    {
        System.out.println( "Hello World!!!!!! :)))))" );
      
        // ZipFileInput z = new ZipFileInput("Enter zip file path here");
        // z.readFiles();

        //ArrayList<ClassInformation> classes = new ArrayList<ClassInformation>();

//         AssignmentSpecPortal assignmentSpecPortal = new AssignmentSpecPortal();
//         assignmentSpecPortal.setVisible(true);

        String zipFilePath = "ZipFolder.zip";

        // Create a File object from the zip file path
        File zipFile = new File(zipFilePath);

        try {
            
            // Create a ZipFileComposite object from the File object
            ZipComponent zipComponent = new ZipFileComposite(zipFile);
    
            ZipFileComposite zipFileComposite = (ZipFileComposite) zipComponent;

            // Iterate over the child components of the zip file composite object
            for (ZipComponent z : zipFileComposite.getComponents()) {
                // z.printInfo();
                if (z instanceof ZipFileComposite)
                {
                    
                    ZipFileComposite w = (ZipFileComposite)z;
                    System.out.println(w.getPath());
                    for (ZipComponent i : w.getComponents())
                    {
                        if (i instanceof ZipEntryLeaf)
                        System.out.println(((ZipEntryLeaf)i).getPath());
                    }
                }
            }

        } catch (IOException e) {
            // Handle the exception
            System.out.println("Unable to read folder. " + e.getMessage());
        }
        }
}

