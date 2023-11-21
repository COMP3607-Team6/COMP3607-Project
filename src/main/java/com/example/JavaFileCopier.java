package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaFileCopier {
    
    public static void javaFileCopierToLeaf (String entryName, ZipEntryLeaf f)
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

    public static void copyFiles(String javaFilePath)
    {

        try
        {
            // JavaFileCopier
            List<Path> p = JavaFileCopier.getJavaFilePaths (javaFilePath);

            for (Path sourcePath : p) {
            
                String output = sourcePath.getFileName().toString();
                Path destinationPath = Paths.get("src", "main", "java", "com", "example", "StudentFile", output);
            
                try {
                    Files.createDirectories(destinationPath.getParent());
            
                    try (BufferedWriter writer = Files.newBufferedWriter(destinationPath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                        FileInputStream fis = new FileInputStream(sourcePath.toFile())) {
            
                        writer.write("package com.example.StudentFile;");
                        writer.newLine();
            
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = fis.read(buffer)) > 0) {
                            writer.write(new String(buffer, 0, len));
                        }
                    }
            
                    System.out.println("File created successfully: " + destinationPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
}

    private  static List<Path> getJavaFilePaths(String dirPath) throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(dirPath))) {
            return paths
                .filter(Files::isRegularFile) // ignore directories
                .filter(path -> path.toString().endsWith(".java")) // filter Java files
                .collect(Collectors.toCollection(ArrayList::new)); // collect into an ArrayList
        }
}
}

