package com.example.ZipFileEntries;

import java.io.*;
import java.util.zip.*;

/*
 * Responsible for creating zip files
 */
public class ZipDirectory {
    /** 
     * Responsible for creating a zip file from a directory
     */
    public static void zipDirectory(String sourceDirectoryPath, String zipFilePath) {
        try {
            FileOutputStream fos = new FileOutputStream(zipFilePath);
            ZipOutputStream zos = new ZipOutputStream(fos);

            File fileToZip = new File(sourceDirectoryPath);

            zipFile(fileToZip, fileToZip.getName(), zos);
            zos.close();
            fos.close();

            System.out.println("Graded Submissions available");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    /** 
     * Helper function that recursively compresses files and directories into a zip archive.
     */
    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zos) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zos.putNextEntry(new ZipEntry(fileName));
                zos.closeEntry();
            } else {
                zos.putNextEntry(new ZipEntry(fileName + "/"));
                zos.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zos);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zos.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }
        fis.close();
    }
}
