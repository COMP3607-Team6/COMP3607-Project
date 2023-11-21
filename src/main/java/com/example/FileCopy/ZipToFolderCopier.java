package com.example.FileCopy;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/*
 * Responsible for copying zip files into folders
 */
public class ZipToFolderCopier implements FileCopier {
     
    /** 
     * This method copies a zip file to a folder
     */
    public static void copyFile(Path source, String destinationFolder)
    {
        // Copy the source file to the target file
        int index = source.toString().lastIndexOf("\\");
        String output = source.toString().substring(index + 1);
        Path target = Paths.get(destinationFolder + output);
        

        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            
            System.out.println("File copied successfully");
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
        }

    }
}
