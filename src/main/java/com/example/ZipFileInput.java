package com.example;

import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipFileInput {
    public String zipFilePath;

    public ZipFileInput(String zipFilePath) 
    {
        this.zipFilePath = zipFilePath;
    }

    public void readFiles()
    {
        try (ZipFile zipFile = new ZipFile(zipFilePath)) {

            Enumeration<? extends ZipEntry> numEntries = zipFile.entries(); //This creates a list of iterable entries

            //Iterate through all items in zip folder
            while (numEntries.hasMoreElements()) {
                ZipEntry entry = numEntries.nextElement();

                //Do something with each entry
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
