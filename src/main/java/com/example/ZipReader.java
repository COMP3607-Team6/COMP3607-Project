package com.example;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipReader {

    public static void main(String[] args) {
        // The path of the outer zip file
        String zipFilePath = "ZipFolder.zip";
        // The temporary directory to store the inner zip files
        String tempDir = "src\\main\\java\\com\\example\\StudentFiles\\";
        // Create a File object from the zip file path
        File zipFile = new File(zipFilePath);
        // Call the readZipFile method with the File object
        readZipFile(zipFile, tempDir);
    }

    // A method that takes a File object representing a zip file and a temporary directory as parameters
    public static void readZipFile(File zipFile, String tempDir) {
        try {
            // Create a ZipFile object from the File object
            ZipFile zip = new ZipFile(zipFile);
            // Get an enumeration of the entries in the zip file
            Enumeration<? extends ZipEntry> entries = zip.entries();
            // Loop through the entries
            while (entries.hasMoreElements()) {
                // Get the next entry
                ZipEntry entry = entries.nextElement();
                // Get the name of the entry
                String name = entry.getName();
                // Print the name of the entry
                // System.out.println("Entry name: " + name);
                // Check if the entry is another zip file
                ZipFileReader.unzip(name,"src\\main\\java\\com\\example\\StudentFiles");
                if (name.endsWith(".zip")) {
                    // Get the input stream of the entry
                    InputStream is = zip.getInputStream(entry);
                    // Create a temporary file from the input stream
                    Path tempFile = Files.createTempFile(Path.of(tempDir), null, null);
                    // Copy the input stream to the temporary file
                    Files.copy(is, tempFile, StandardCopyOption.REPLACE_EXISTING);
                    // Close the input stream
                    is.close();
                    // Call the readZipFile method recursively with the temporary file
                    readZipFile(tempFile.toFile(), tempDir);
                    // Delete the temporary file
                    Files.delete(tempFile);
                } else {
                    // The entry is not a zip file, so you can read its contents as usual
                    // For example, you can get the size of the entry
                    long size = entry.getSize();
                    // Print the size of the entry
                    // System.out.println("Entry size: " + size + " bytes");
                }
            }
            // Close the zip file
            zip.close();
        } catch (IOException e) {
            // Handle the exception
            e.printStackTrace();
        }
    }


}
