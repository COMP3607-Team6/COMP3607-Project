package com.example;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ZipToFolderCopier implements FileCopier {
    
    public static Path copyFile(Path source, String destinationFolder)
    {
        // Path source = Paths.get(w.getPath());
        // Create a Path object for the target file

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

        return target;
    }
}
