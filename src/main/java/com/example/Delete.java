package com.example;

import java.io.File;
import org.apache.commons.io.FileUtils;
import java.io.IOException;

/**
 * Contains functions to delete files/folders
*/
public class Delete {

        
/**
 * A custom method to delete a folder recursively
*/
public static void deleteFolder(String folderPath) {

    File folder = new File(folderPath);
    // Get all the files and subfolders in the folder
        File[] files = folder.listFiles();
        // Check if the array is not null
        if (files != null) {
            // Loop over the files and subfolders and delete them
            for (File file : files) {
            
                // If the file is a directory, call the method recursively
                if (file.isDirectory()) {
                    deleteFolder(file.toString());
                }

                 file.delete();
                
                if(file.exists()){
                    System.out.println("Didn't remove file");
                }
            }
        }
        // Delete the folder itself
        folder.delete();
    }


/**
 * A custom method to delete a folder recursively
*/
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

/**
 * A custom method to delete all the files within a folder but not the folder itself
*/
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


/**
 * A custom method to delete all the files within a folder but not the folder itself
*/
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

/**
 * Finds the subfolders of a folder and deletes it.
*/
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
