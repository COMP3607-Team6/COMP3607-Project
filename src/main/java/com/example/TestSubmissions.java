package com.example;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.fontbox.afm.Composite;

public class TestSubmissions {
    

    // This method takes a folder path as a parameter and prints the names of the outer folders in it
    public static void printOuterFolders(String folderPath) {
        // Create a File object from the folder path
        File folder = new File(folderPath);
        // Check if the folder exists and is a directory
        if (folder.exists() && folder.isDirectory()) {
            // Create a FileFilter that accepts only directories and not files
            FileFilter directoryFilter = new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return file.isDirectory();
                }
            };
            // Get the array of files and directories that match the filter
            File[] outerFolders = folder.listFiles(directoryFilter);
            // Check if the array is not null
            if (outerFolders != null) {
                // Iterate over the array and print the names of the outer folders
                for (File outerFolder : outerFolders) {
                    
                    System.out.println(outerFolder.getName());
                }
            }
        }
    }

    public static void copySubmission()
    {

    }

    public static void copyZipFolder(String sourceFolderPath, String destinationFolderPath) throws IOException {
        // Create a File object from the source folder path
        File sourceFolder = new File(sourceFolderPath);
        // Check if the source folder exists and is a directory
        if (sourceFolder.exists() && sourceFolder.isDirectory()) {
            // Create a File object from the destination folder path
            File destinationFolder = new File(destinationFolderPath);
            // Check if the destination folder exists and is a directory
            if (destinationFolder.exists() && destinationFolder.isDirectory()) {
                // Get the list of files and directories in the source folder
                File[] files = sourceFolder.listFiles();
                // Check if the list is not null
                if (files != null) {
                    // Iterate over the list and copy the zip folders to the destination folder
                    for (File file : files) {
                    // Check if the file is a zip folder
                    if (file.isDirectory() && file.getName().endsWith(".zip")) {
                        // Create a Path object from the file
                        Path sourcePath = file.toPath();
                        // Create a Path object from the destination folder and the file name
                        Path targetPath = new File(destinationFolder, file.getName()).toPath();
                        // Copy the file to the destination folder, replacing the existing file if any
                        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    }
                }
                }
            } else {
                // Throw an exception if the destination folder does not exist or is not a directory
                throw new IOException("The destination folder does not exist or is not a directory");
            }
        } else {
            // Throw an exception if the source folder does not exist or is not a directory
            throw new IOException("The source folder does not exist or is not a directory");
        }
    }


    public static void main(String[] args) {
        // Create a File object for the root directory

        String zipFilePath = "ZipFolder.zip";

        // Create a File object from the zip file path
        File zipFile = new File(zipFilePath);

        try {
            
            // Create a ZipFileComposite object from the File object
            ZipComponent zipComponent = new ZipFileComposite(zipFile);
    
            ZipFileComposite zipFileComposite = (ZipFileComposite) zipComponent;

            // Iterate over the child components of the zip file composite object
            for (ZipComponent z : zipFileComposite.getComponents()) {
                // z.printInfo();
                if (z instanceof ZipFileComposite)
                {
                    
                    ZipFileComposite w = (ZipFileComposite)z;
                    // System.out.println(w.getPath());
                    // copyZipFolder(w.getPath(), "src\\main\\java\\com\\example\\StudentFile");

                    // Create a Path object for the source file
         // Create a Path object for the source file
         Path source = Paths.get(w.getPath());
         // Create a Path object for the target file
         // Use a different name or append a suffix to the source file name
         Path target = Paths.get("src\\main\\java\\com\\example\\StudentFile\\test_copy.zip");
         // Copy the source file to the target file, replacing the existing file if any

         try {
             Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
             System.out.println("File copied successfully");
         } catch (IOException e) {
             // Handle the exception
             e.printStackTrace();
         }


                    for (ZipComponent i : w.getComponents())
                    {
                        if (i instanceof ZipEntryLeaf)
                        // System.out.println(((ZipEntryLeaf)i).getPath());
                        {
                            ZipEntryLeaf f = (ZipEntryLeaf)i;
                            // OutputStream output = new FileOutputStream("src\\main\\java\\com\\example\\StudentFile");

                            Path path = Paths.get(f.getPath());
                            InputStream input = Files.newInputStream(path, StandardOpenOption.READ);
                            
                            String outputFolder = "src\\main\\java\\com\\example\\StudentFile";
                            String entryName = f.getPath();
                            System.out.println(entryName);
                            if (entryName.endsWith(".java"))
                            {
                                Path sourcePath = Paths.get(entryName);
                                System.out.println("Source: " + sourcePath);

                                Path dest = Paths.get(outputFolder);
                                System.out.println("Dest " + dest);

                                File sourceFile = new File(entryName);
                                File destinationFile = new File("src\\main\\java\\com\\example\\StudentFile\\" + f.get_rel_path());
                                System.out.println("Dest File " + destinationFile);
 
                                Path entryPath = Paths.get(outputFolder, entryName);
                                System.out.println("EntryPath " + entryPath.getFileName());
                                
                                String p = "src" + File.separator +"main" + File.separator + "java" + File.separator + 
                                "com"  + File.separator + "example" + File.separator + "StudentFile" + File.separator + f.get_rel_path();
                                System.out.println(p);
                                p = "src\\main\\java\\com\\example\\StudentFile\\" + f.get_rel_path();
                                Path filePath = Paths.get(p);
                                Files.createDirectories(filePath.getParent());
                            try {
                            // Create the file using the Files.createFile (Path) method
                            Files.createFile(filePath);

                            try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                                    // Add the package declaration to the beginning of the file
        
                                    // Path sourcePath = Paths.get(outputFolder);
                                    // Create a Path object for the file folder
                                    // Path filePath = Paths.get(outputFolder, entryName);
                                    // Get the relative path of the file from the source folder
                                    Path relativePath = sourcePath.relativize(filePath);
        
                                    // Find the index of the last \ in the string
                                    int lastIndex = filePath.toString().lastIndexOf("\\");
        
                                    // Get the substring that starts from the beginning and stops before the last \ in the string
                                    String assignmentFolderPath = filePath.toString().substring(0, lastIndex);
        
                                    String packageName = relativePath.toString().replace("/", ".");
                                    packageName = relativePath.toString().replace("\\", ".");
                                    // Remove the file extension from the package name
                                    packageName = packageName.substring(0, packageName.lastIndexOf("."));
                                    packageName = packageName.substring(0, packageName.lastIndexOf("."));
                                    // Write the package name to the file
                                    packageName = packageName.replaceAll("^\\.+|", "");
                                    System.out.println(packageName);
                                    writer.write("package com.example." + packageName + ";");
        
        
                                    writer.newLine();
        
                                    // Copy the contents of the Java file
                                    byte[] buffer = new byte[1024];
                                    int len;

                                    FileInputStream zis = new FileInputStream(entryName);
                                    while ((len = zis.read(buffer)) > 0) {
                                        writer.write(new String(buffer, 0, len));
                                    }
                                }
        
                            // Print a success message
                            System.out.println("File created successfully: " + filePath);
                            } catch (IOException e) {
                            // Handle the exception
                            e.printStackTrace();
                            }
                                // Assume dest is a Path object for the destination file
                                // path = 
                                // File newFile = new File(entryPath.toString());
                                // Files.createFile (destinationFile.toPath());
                                // Write some content to the file using a BufferedWriter


                                // Files.createDirectories(outputFolder);

                                // try (BufferedWriter writer = Files.newBufferedWriter(entryPath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                                //     // Add the package declaration to the beginning of the file
        
                                //     Path sourcePath = Paths.get(outputFolder);
                                //     // Create a Path object for the file folder
                                //     Path filePath = Paths.get(outputFolder, entryName);
                                //     // Get the relative path of the file from the source folder
                                //     Path relativePath = sourcePath.relativize(filePath);
        
                                //     // Find the index of the last \ in the string
                                //     int lastIndex = filePath.toString().lastIndexOf("\\");
        
                                //     // Get the substring that starts from the beginning and stops before the last \ in the string
                                //     String assignmentFolderPath = filePath.toString().substring(0, lastIndex);
        
                                //     String packageName = relativePath.toString().replace("/", ".");
                                //     packageName = relativePath.toString().replace("\\", ".");
                                //     // Remove the file extension from the package name
                                //     packageName = packageName.substring(0, packageName.lastIndexOf("."));
                                //     packageName = packageName.substring(0, packageName.lastIndexOf("."));
                                //     // Write the package name to the file
                                //     writer.write("package com.example.StudentFiles." + packageName + ";");
        
        
                                //     writer.newLine();
        
                                //     // Copy the contents of the Java file
                                //     byte[] buffer = new byte[1024];
                                //     int len;

                                //     FileInputStream zis = new FileInputStream(entryName);
                                //     while ((len = zis.read(buffer)) > 0) {
                                //         writer.write(new String(buffer, 0, len));
                                //     }
                                // }
                                
                            }


                        }
    }
                }
            }

        } catch (IOException e) {
            // Handle the exception
            System.out.println("Unable to read folder. " + e.getMessage());
        }
        

        File folderPath = new File("src\\main\\java\\com\\example\\StudentFiles");
        // Call the printFolders method with the root directory
        // printOuterFolders("src\\main\\java\\com\\example\\StudentFiles");
    }
}





