package com.example;

import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.io.File;
import java.io.IOException;

// This is a subclass of ZipEntryComponentFactory that creates ZipFileComposite objects 
public class ZipFileComponentFactory extends ZipEntryComponentFactory { 
    // This is a static method that takes a ZipEntry object and a ZipFile object as parameters 
    // and returns a ZipFileComposite object 
    public static ZipFileComposite createZipFileComposite(ZipEntry zipEntry, ZipFile zipFile) throws IOException { 
        // Use the TempFileCreator class to create a temporary file from the zip entry 
        File tempFile = TempFileCreator.createTempFileFromZipEntry(zipEntry, zipFile); 
        // Create a ZipFileComposite object from the temporary file 
        return new ZipFileComposite(tempFile); 
    } 
}
