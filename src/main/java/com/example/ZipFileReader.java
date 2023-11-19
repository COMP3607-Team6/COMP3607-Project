package com.example;
import java.io.*;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class ZipFileReader {
    public String unzip(String zipFilePath, String outputFolder) {
        try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(Paths.get(zipFilePath)))) {
            ZipEntry zipEntry;
            String assignmentFolderPath = "";

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

                            Path sourcePath = Paths.get(outputFolder);
                            // Create a Path object for the file folder
                            Path filePath = Paths.get(outputFolder, entryName);
                            // Get the relative path of the file from the source folder
                            Path relativePath = sourcePath.relativize(filePath);

                            // Find the index of the last \ in the string
                            int lastIndex = filePath.toString().lastIndexOf("\\");

                            // Get the substring that starts from the beginning and stops before the last \ in the string
                            assignmentFolderPath = filePath.toString().substring(0, lastIndex);

                            String packageName = relativePath.toString().replace("/", ".");
                            packageName = relativePath.toString().replace("\\", ".");
                            // Remove the file extension from the package name
                            packageName = packageName.substring(0, packageName.lastIndexOf("."));
                            packageName = packageName.substring(0, packageName.lastIndexOf("."));
                            // Write the package name to the file
                            writer.write("package com.example.StudentFiles." + packageName + ";");


                            writer.newLine();

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
            return assignmentFolderPath;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
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

// A custom method to delete all the files within a folder but not the folder itself
public static void deleteFilesInFolder(String folderPath) {
    // Create a File object for the folder
    File folder = new File(folderPath);
    // Get all the files and subfolders in the folder
    File[] files = folder.listFiles();
    // If the folder is not empty, delete each file/subfolder
    if (files != null) {
        for (File file : files) {
            // Delete the file or subfolder
            file.delete();
        }
    }
}

// A custom method to delete all the files within a folder but not the folder itself
public static void deleteFilesInFolder(File folder) {
    
    // Get all the files and subfolders in the folder
    File[] files = folder.listFiles();
    // If the folder is not empty, delete each file/subfolder
    if (files != null) {
        for (File file : files) {
            // Delete the file or subfolder
            file.delete();
        }
    }
}

public static void deleteSubFolders(String folderName)
{
    File parentFolder = new File(folderName);
    // Get the list of sub folders in the parent folder
    File[] subFolders = parentFolder.listFiles(File::isDirectory);
    // Loop through the sub folders and delete them
    for (File subFolder : subFolders) {
      try {
        // Delete the sub folder using the FileUtils.deleteDirectory() method
        FileUtils.deleteDirectory(subFolder);
        // Print a success message
        System.out.println("Sub folder deleted successfully: " + subFolder);
      } catch (IOException e) {
        // Handle the exception
        e.printStackTrace();
      }
    }
}

}