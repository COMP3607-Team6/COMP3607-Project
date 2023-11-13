package com.example;
import java.io.*;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFileReader {
    public static void unzip(String zipFilePath, String outputFolder) {
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
        // String zipFilePath = "src\\main\\java\\com\\example\\Avinash_Roopnarine_816029635_A2.zip";
        String outputFolder = "src\\main\\java\\com\\example\\Student-Files";

        // unzip(zipFilePath, outputFolder);
        unzip("src\\main\\java\\com\\example\\Avinash_Roopnarine_816029635_A2.zip", outputFolder);
        unzip("ZipFolder\\Avinash_Roopnarine_816029635_A2-2.zip.zip", outputFolder);
        System.out.println("Java files unzipped and modified successfully.");
    }
}