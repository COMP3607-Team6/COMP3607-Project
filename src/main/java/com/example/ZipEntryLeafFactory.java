package com.example;

import java.io.InputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

// This is a subclass of ZipEntryComponentFactory that creates ZipEntryLeaf objects 
public class ZipEntryLeafFactory extends ZipEntryComponentFactory { 
    
    public static ZipEntryLeaf createZipEntryLeaf(ZipEntry zipEntry, ZipFile zipFile) throws IOException { 
        // Check if the zip entry name ends with “.java” 
        // System.out.println("IA");
            // Get the input stream of the zip entry 
            InputStream inputStream = zipFile.getInputStream(zipEntry); 
            // Create a ZipEntryLeaf object from the zip entry and the input stream 
            return new ZipEntryLeaf(zipEntry, inputStream); 
    } 
}
