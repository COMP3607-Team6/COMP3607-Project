package com.example.FileCopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/*
 * Responsible for copying files into a zip file
 */
public class FileToZipCopier implements FileCopier {
    /** 
     * This function copies a specified file to a zip file
     */
    public static void copy(File fileToAdd, File zipFile)
    {
        try
        {
            // get a temp file
            File tempFile = File.createTempFile(zipFile.getName(), null);
            // delete it, otherwise you cannot rename your existing zip to it.
            tempFile.delete();

            boolean renameOk = zipFile.renameTo(tempFile);
            if (!renameOk) {
                throw new RuntimeException("could not rename the file " + zipFile.getAbsolutePath() + " to " + tempFile.getAbsolutePath());
            }

            byte[] buf = new byte[4096 * 1024];

            ZipInputStream zin = new ZipInputStream(new FileInputStream(tempFile));
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));

            ZipEntry entry = zin.getNextEntry();
            while (entry != null) {
                String name = entry.getName();
                boolean notInFiles = !fileToAdd.getName().equals(name);
                if (notInFiles) {
                    // Add ZIP entry to output stream.
                    out.putNextEntry(new ZipEntry(name));
                    // Transfer bytes from the ZIP file to the output file
                    int len;
                    while ((len = zin.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                }
                entry = zin.getNextEntry();
            }
            // Close the streams
            zin.close();

            // Compress the files
            InputStream in = new FileInputStream(fileToAdd);
            // Add ZIP entry to output stream.
            out.putNextEntry(new ZipEntry(fileToAdd.getName()));
            // Transfer bytes from the file to the ZIP file
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            // Complete the entry
            out.closeEntry();
            in.close();
            // Complete the ZIP file
            out.close();
            tempFile.delete();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
