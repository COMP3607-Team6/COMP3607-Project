package com.example;
import java.io.*;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;



public class ZipFileReader implements Unzip{

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

}