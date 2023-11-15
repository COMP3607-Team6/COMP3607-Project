package com.example;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

// The Leaf class represents a Java file that can be contained in a zip file
public class Leaf implements Component {
  private String name; // the name of the Java file

  public Leaf(String name) {
    this.name = name;
  }

  // copy the Java file to a destination folder or a zip output stream
  public void copy(String zipFilePath, String outputFolder) {
    
    // System.out.println(zipFilePath + " " + outputFolder);
        try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(Paths.get(zipFilePath)))) {
            ZipEntry zipEntry;

            while ((zipEntry = zis.getNextEntry()) != null) {
                String entryName = zipEntry.getName();
                System.out.println("NAme " + entryName);
                if (entryName.endsWith(".java")) {
                    Path entryPath = Paths.get(outputFolder, entryName);

                    if (zipEntry.isDirectory()) {
                        Files.createDirectories(entryPath);
                    } else {
                        Files.createDirectories(entryPath.getParent());

                        try (BufferedWriter writer = Files.newBufferedWriter(entryPath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                            // Add the package declaration to the beginning of the file
                            writer.write("package com.example.StudentFiles." + "file1;");
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

  // save a PDF file to the Java file
  public void savePDF(String pdfName) {
    // not applicable for Java files
    throw new UnsupportedOperationException();
  }

  // delete the Java file
  public void delete() {
    // delete the Java file
    File file = new File(name);
    file.delete();
  }
}