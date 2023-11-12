package com.example;
import java.io.*;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFileReader {
    public static void unzip(String zipFilePath, ZipInputStream zis, String outputFolder) {
        try {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                String entryName = zipEntry.getName();
                if (entryName.equals(entryName) && entryName.endsWith(".java")) {
                    Path entryPath = Paths.get(outputFolder, entryName);

                    if (zipEntry.isDirectory()) {
                        Files.createDirectories(entryPath);
                        System.out.println("HI");
                    } else {
                        System.out.println("ON");
                        Files.createDirectories(entryPath.getParent());

                        try (BufferedWriter writer = Files.newBufferedWriter(entryPath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                            // Add the package declaration to the beginning of the file
                            System.out.println("Yes");
                            writer.write("package com.example.Avinash_Roopnarine_816029635_A2;");
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String zipFilePath = "C:\\Users\\Avinash Roopnarine\\Desktop\\OOP 2\\COMP3607-Project\\src\\main\\java\\com\\example\\Avinash_Roopnarine_816029635_A2.zip";
        String outputFolder = "C:\\Users\\Avinash Roopnarine\\Desktop\\OOP 2\\COMP3607-Project\\src\\main\\java\\com\\example";

        // Zunzip(zipFilePath, outputFolder);
        System.out.println("Java files unzipped and modified successfully.");
    }
}