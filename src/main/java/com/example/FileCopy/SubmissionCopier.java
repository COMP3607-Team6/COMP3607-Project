package com.example.FileCopy;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import com.example.ZipFileEntries.ZipComponent;

import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * Responsible for copying student submissions for Testing
 */
public class SubmissionCopier implements FileCopier{

    /** 
     * This method copies a file to a target directory and returns its path.
     */
    public static Path copySubmission(ZipComponent w)
    {
        Path source = Paths.get(w.getPath());
        // Create a Path object for the target file

        // Copy the source file to the target file
        int index = w.getPath().lastIndexOf("\\");
        String output = w.getPath().substring(index + 1);
        Path target = Paths.get("src\\main\\java\\com\\example\\StudentFile\\" + output);
        

        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            
            System.out.println("File copied successfully");
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
        }

        return target;
    }
}
