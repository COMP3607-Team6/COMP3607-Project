package com.example;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

// This is an abstract class that defines the factory method for creating ZipComponent objects 
public abstract class ZipEntryComponentFactory { 
    // This is a static method that takes a ZipEntry object and a ZipFile object as parameters 
    // and returns a ZipComponent object 
    public static ZipComponent createZipComponent(ZipEntry zipEntry, ZipFile zipFile) throws IOException { 
        // Check if the zip entry is another zip file 
        if (zipEntry.getName().endsWith(".zip")) { 
            // Use the ZipFileComponentFactory subclass to create a ZipFileComposite object 
            return ZipFileComponentFactory.createZipFileComposite(zipEntry, zipFile); } 
            else { 
                // Use the ZipEntryLeafComponentFactory subclass to create a ZipEntryLeaf object
                    return ZipEntryLeafFactory.createZipEntryLeaf(zipEntry, zipFile); }
            }
        
}
