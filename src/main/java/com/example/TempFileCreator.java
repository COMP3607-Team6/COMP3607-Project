package com.example;
import java.util.zip.ZipEntry;
import java.nio.file.Files;
import java.io.File;
import java.util.zip.ZipFile;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.io.IOException;

public class TempFileCreator { 
    // This is a static method that takes a ZipEntry object and a ZipFile object as parameters 
    // and returns a File object that copies the contents of the zip entry 
    public static File createTempFileFromZipEntry(ZipEntry zipEntry, ZipFile zipFile) throws IOException { 
        // Create a temporary file 
        Path tempFile = Files.createTempFile(null, null);
        // Copy the contents of the zip entry to the temporary file 
        Files.copy(zipFile.getInputStream(zipEntry), tempFile, StandardCopyOption.REPLACE_EXISTING); 
        // Return the temporary file as a File object 
        return tempFile.toFile(); 
    } 
}
