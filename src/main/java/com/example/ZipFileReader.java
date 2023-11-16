package com.example;
import java.io.*;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFileReader {
    public static void unzip(String zipFilePath, String outputFolder) {
        // System.out.println(zipFilePath + " " + outputFolder);
        try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(Paths.get(zipFilePath)))) {
            ZipEntry zipEntry;

            while ((zipEntry = zis.getNextEntry()) != null) {
                String entryName = zipEntry.getName();

                if (entryName.endsWith(".java")) {
                    Path entryPath = Paths.get(outputFolder, entryName);

                    if (zipEntry.isDirectory()) {
                        Files.createDirectories(entryPath);
                    } else {
                        Files.createDirectories(entryPath.getParent());

                        try (BufferedWriter writer = Files.newBufferedWriter(entryPath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                            // Add the package declaration to the beginning of the file
                            // writer.write("package com.example.StudentFiles." + "file1;");

                            // Get the folder name from the entry name
                            String folderName = entryName.substring(0, entryName.indexOf("/"));
                            // Write the package name with the folder name
                            writer.write("package com.example.StudentFiles." + folderName + ";");

                            writer.newLine();
// System.out.println(outputFolder + " " + entryName);
                            // Copy the contents of the Java file
                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len = zis.read(buffer)) > 0) {
                                writer.write(new String(buffer, 0, len));
                            }
                        }
                    }

                }

                zis.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    // A custom method to delete a folder recursively
public static void deleteFolder(String folderPath) {
    // Create a File object for the folder
    File folder = new File(folderPath);
    // Get all the files and subfolders in the folder
    File[] files = folder.listFiles();
    // If the folder is not empty, delete each file/subfolder
    if (files != null) {
        for (File file : files) {
            // If the file is a directory, call the method recursively
            if (file.isDirectory()) {
                deleteFolder(file.getPath());
            }
            // Delete the file
            file.delete();
        }
    }
    // Delete the folder
    folder.delete();
}

// A custom method to delete a folder recursively
public static void deleteFolder(File folder) {
    // Get all the files and subfolders in the folder
    File[] files = folder.listFiles();
    // If the folder is not empty, delete each file/subfolder
    if (files != null) {
        for (File file : files) {
            // If the file is a directory, call the method recursively
            if (file.isDirectory()) {
                deleteFolder(file);
            }
            // Delete the file
            file.delete();
        }
    }
    // Delete the folder
    folder.delete();
}


    public static void main(String[] args) {
        // String zipFilePath = "src\\main\\java\\com\\example\\Avinash_Roopnarine_816029635_A2.zip";
        String outputFolder = "src\\main\\java\\com\\example\\StudentFiles";

        // unzip("ZipFolder/assignment.zip", outputFolder);
        // unzip("src\\main\\java\\com\\example\\assignment.zip", outputFolder);
        // unzip("src\\main\\java\\com\\example\\Avinash_Roopnarine_816029635_A2.zip", outputFolder);
        // File f = new File ("src\\main\\java\\com\\example\\assignment.zip");
        // unzip("ZipFolder\\Avinash_Roopnarine_816029635_A2-2.zip.zip", outputFolder);
        System.out.println("Java files unzipped and modified successfully.");

        
        // File f = new File ("src\\main\\java\\com\\example\\StudentFiles\\vinash_Roopnarine_816029635_A2");
        // ZipFileReader.deleteFolder("src\\main\\java\\com\\example\\StudentFiles\\Avinash_Roopnarine_816029635_A2");

    }
}