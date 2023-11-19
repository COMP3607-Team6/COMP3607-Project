package com.example;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SubmissionCopier implements FileCopier{

    public static Path copySubmission(ZipComponent w)
    {
        Path source = Paths.get(w.getPath());
        // Create a Path object for the target file
        // Use a different name or append a suffix to the source file name
        System.out.println(w.getPath());

        int index = w.getPath().lastIndexOf("\\");
        // Get the substring after the last \ character
        String output = w.getPath().substring(index + 1);
        Path target = Paths.get("src\\main\\java\\com\\example\\StudentFile\\" + output);
        // Copy the source file to the target file, replacing the existing file if any

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
