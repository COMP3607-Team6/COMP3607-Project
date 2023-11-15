package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import java.io.File;

public class ZipFileExtractor {

    
    // public static void main(String[] args) throws IOException {
    //     // The path of the zip file
    //     String zipFilePath = "ZipFolder.zip";
    //     // The path of the output folder
    //     String outputFolder = "src\\main\\java\\com\\example\\StudentFiles";
    //     // Create a ZipInputStream object to read the zip file
    //     ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath));
    //     // Get the next entry in the zip file
    //     ZipEntry zipEntry = zis.getNextEntry();
    //     // Iterate over the entries in the zip file
    //     while (zipEntry != null) {
    //         // Get the name of the entry
    //         String entryName = zipEntry.getName();
    //         System.out.println(entryName);
    //         // Check if the entry is a zip file by its name or type
    //         if (entryName.endsWith(".zip") || zipEntry.isDirectory()) {
    //             // Get the full path of the zip file in the output folder
    //             String outputZipFilePath = outputFolder + "/" + entryName;
    //             // Check if the file exists and is accessible
    //             System.out.println("before");
    //             File file = new File(outputZipFilePath);
    //             new File(outputZipFilePath).mkdirs();
    //             System.out.println(file);
    //             String filename = file.getName();

    //             ZipFileReader.unzip(filename, "src\\main\\java\\com\\example\\StudentFile" );
    //             // ZipFileReader.deleteFolder(file);
    //             if (file.exists() && file.canWrite()) {
    //                 // System.out.println("HIIIIIIIIIIIIIIII");
    //                 // Create a ZipOutputStream object to write the zip file to the output folder
    //                 ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(file));
    //                 // Copy the bytes for the zip file
    //                 byte[] buffer = new byte[1024];
    //                 int length;
    //                 while ((length = zis.read(buffer)) > 0) {
    //                     zos.write(buffer, 0, length);
    //                 }
    //                 // Close the zip file
    //                 zos.close();
    //             } else {
    //                 // Print an error message
    //                 System.out.println("The file " + outputZipFilePath + " does not exist or is not accessible.");
    //             }
    //         }
    //         // Close the entry
    //         zis.closeEntry();
    //         // Get the next entry in the zip file
    //         zipEntry = zis.getNextEntry();
    //     }
    //     // Close the zip file
    //     zis.close();
    // }
}

