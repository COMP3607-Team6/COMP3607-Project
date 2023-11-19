package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class JavaFileCopier {
    
    public static void javaFileCopier(String entryName, ZipEntryLeaf f)
    {
        if (entryName.endsWith(".java"))
        {
            Path sourcePath = Paths.get(entryName);
            
            int index = f.get_rel_path().lastIndexOf("\\");
            // Get the substring after the last \ character
            String output = f.get_rel_path().substring(index + 1);   
            File destinationFile = new File("src\\main\\java\\com\\example\\StudentFile\\" + output);  
            // destinationFile.deleteOnExit();    
            String studentJavaFileName = "src\\main\\java\\com\\example\\StudentFile\\" + output;
            Path filePath = Paths.get(studentJavaFileName);
            try{
                Files.createDirectories(filePath.getParent());
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
                                
            try {
            // Create the file using the Files.createFile (Path) method
                if (!Files.exists(filePath))
                {
                    Files.createFile(filePath);
                }

                try (BufferedWriter writer = Files.newBufferedWriter(destinationFile.toPath(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                        // Add the package declaration to the beginning of the file

                        Path relativePath = sourcePath.relativize(filePath);

                        String packageName = relativePath.toString().replace("/", ".");
                        packageName = relativePath.toString().replace("\\", ".");
                        // Remove the file extension from the package name
                        packageName = packageName.substring(0, packageName.lastIndexOf("."));
                        packageName = packageName.substring(0, packageName.lastIndexOf("."));
                        // Write the package name to the file
                        packageName = packageName.replaceAll("^\\.+|", "");

                        writer.write("package com.example." + packageName + ";");


                        writer.newLine();

                        // Copy the contents of the Java file
                        byte[] buffer = new byte[1024];
                        int len;

                        FileInputStream zis = new FileInputStream(entryName);
                        while ((len = zis.read(buffer)) > 0) {
                            writer.write(new String(buffer, 0, len));
                        }
                        zis.close();
                        writer.close();
                    }

                System.out.println("File created successfully: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
                
                
            }
    }
}
