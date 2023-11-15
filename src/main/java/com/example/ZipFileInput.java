package com.example;

import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.io.File;
import com.example.ZipFileReader;

public class ZipFileInput {
    public String zipFilePath;

    public ZipFileInput(String zipFilePath) 
    {
        this.zipFilePath = zipFilePath;
    }

    public void readFiles(String outputFolder)
    {
        
        try (ZipFile zipFile = new ZipFile(zipFilePath)) {

            Enumeration<? extends ZipEntry> numEntries = zipFile.entries(); //This creates a list of iterable entries

            //Iterate through all items in zip folder
            while (numEntries.hasMoreElements()) {
                ZipEntry entry = numEntries.nextElement();

                //Do something with each entry
                try (ZipInputStream zis = new ZipInputStream(zipFile.getInputStream(entry))) {
                    
                    String s = entry.getName();
                    s = s.replaceAll("/", "\\\\\\\\");
                    // s = "ZipFolder\\\\" + s;
                    System.out.println("S " + s);

                    
                    ZipFileReader.unzip(s, outputFolder);
                    
                    
                } //marker
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String zipFilePath = "ZipFolder.zip";
        String outputFolder = "src\\main\\java\\com\\example\\StudentFiles";

        ZipFileInput z = new ZipFileInput(zipFilePath);

        z.readFiles(outputFolder);
        // Zunzip(zipFilePath, outputFolder);
        System.out.println("Java files unzipped and modified successfully.");
    }
}


