package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;






public class ZipFileProcessor {
    // The method to process a zip folder that contains Java files
public void processZipFolder(String zipFolderPath, String outputFolder) {
    try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(Paths.get(zipFolderPath)))) {
        ZipEntry zipEntry;
        // Create a temporary zip file to store the PDF file
        File tempZipFile = File.createTempFile("temp", ".zip");
        // Create a ZipOutputStream to write the PDF file to the temp zip file
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(tempZipFile));
        // A flag to indicate if the PDF file is added
        boolean pdfAdded = false;
        // Iterate over the entries in the zip folder
        while ((zipEntry = zis.getNextEntry()) != null) {
            String entryName = zipEntry.getName();
            if (entryName.endsWith(".java")) {
                // If the entry is a Java file, extract it to the output folder
                Path entryPath = Paths.get(outputFolder, entryName);
                if (zipEntry.isDirectory()) {
                    Files.createDirectories(entryPath);
                } else {
                    Files.createDirectories(entryPath.getParent());
                    try (BufferedWriter writer = Files.newBufferedWriter(entryPath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                        // Copy the contents of the Java file
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            writer.write(new String(buffer, 0, len));
                        }
                    }
                }
            } else {
                // If the entry is not a Java file, copy it to the temp zip file
                zos.putNextEntry(new ZipEntry(entryName));
                byte[] buffer = new byte[1024];
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
                // If the entry is a PDF file, set the flag to true
                if (entryName.endsWith(".pdf")) {
                    pdfAdded = true;
                }
            }
            zis.closeEntry();
        }
        // Do some processing on the Java files in the output folder
        // Generate a PDF file for the zip folder
        // File pdfFile = generatePdf(zipFolderPath, outputFolder);
        File pdfFile = new File ("src\\main\\java\\com\\example\\Output.pdf");
        // Add the PDF file to the temp zip file
        zos.putNextEntry(new ZipEntry(pdfFile.getName()));
        FileInputStream fis = new FileInputStream(pdfFile);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) > 0) {
            zos.write(buffer, 0, len);
        }
        zos.closeEntry();
        fis.close();
        // Delete the original PDF file if it exists
        if (pdfAdded) {
            deletePdf(zipFolderPath);
        }
        // Close the ZipOutputStream
        zos.close();
        // Replace the original zip folder with the temp zip file
        Files.move(tempZipFile.toPath(), Paths.get(zipFolderPath), StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// The method to generate a PDF file for a zip folder
// public File generatePdf(String zipFolderPath, String outputFolder) {
//     // The code to generate a PDF file based on the Java files in the output folder
//     // Return the PDF file
// }

// The method to delete the PDF file in a zip folder
public void deletePdf(String zipFolderPath) {
    // The code to delete the PDF file in the zip folder
}

public static void append() 
{
    // The path of the existing zip file
    String zipFilePath = "src\\main\\java\\com\\example\\Avinash_Roopnarine_816029635_A2.zip";
    // The path of the file to be appended
    String filePath = "src\\main\\java\\com\\example\\Output.pdf";
    // Create a temporary zip file to store the existing entries and the new file
    try {
        File tempZipFile = File.createTempFile("temp", ".zip");
    // Delete the temporary file
    tempZipFile.delete();
    // Rename the original zip file to the temporary file
    if (!new File(zipFilePath).renameTo(tempZipFile)) {
        throw new IOException("Could not rename the file " + zipFilePath);
    }
    // Create a ZipInputStream object to read from the temporary file
    ZipInputStream zis = new ZipInputStream(new FileInputStream(tempZipFile));
    // Create a ZipOutputStream object to write to the original file
    ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFilePath));
    // Iterate over the entries in the temporary file
    ZipEntry entry = zis.getNextEntry();
    while (entry != null) {
        // Copy the entry to the original file
        zos.putNextEntry(entry);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = zis.read(buffer)) > 0) {
            zos.write(buffer, 0, len);
        }
        zos.closeEntry();
        zis.closeEntry();
        entry = zis.getNextEntry();
    }
    // Create a ZipEntry object to represent the file to be appended
    ZipEntry zae = new ZipEntry(filePath);
    // Put the entry to the original file
    zos.putNextEntry(zae);
    // Write the bytes for the file
    FileInputStream fis = new FileInputStream(filePath);
    byte[] buffer = new byte[1024];
    int len;
    while ((len = fis.read(buffer)) > 0) {
        zos.write(buffer, 0, len);
    }
    zos.closeEntry();
    fis.close();
    // Close the zip file
    zos.close();
    // Close the temporary file
    zis.close();
    // Delete the temporary file
    tempZipFile.delete();
    }
    catch (IOException i){

    }

}

// The main method that iterates over the zip folders in y
public static void main(String[] args) {
    // The path of the zip folder y
    // String y = "ZipFolder.zip";
    // // The output folder for the Java files
    // String outputFolder = "src\\main\\java\\com\\example\\Student-Files";
    // // Create an instance of the class
    // ZipFileProcessor zfp = new ZipFileProcessor();
    // // Iterate over the zip folders in y
    // try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(Paths.get(y)))) {
    //     ZipEntry zipEntry;
    //     while ((zipEntry = zis.getNextEntry()) != null) {
    //         String entryName = zipEntry.getName();
    //         if (entryName.endsWith(".zip")) {
    //             // If the entry is a zip folder, process it
    //             zfp.processZipFolder(entryName, outputFolder);
    //         }
    //         zis.closeEntry();
    //     }
    // } catch (IOException e) {
    //     e.printStackTrace();
    // }

    ZipFileProcessor.append();
}

}
