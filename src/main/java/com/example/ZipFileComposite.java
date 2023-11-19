package com.example;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;



// This is the ZipFileComposite class that represents a zip file
public class ZipFileComposite implements ZipComponent, AutoCloseable {
    // This is the ZipFile object that wraps the actual zip file
    private ZipFile zipFile;
    // This is the list of child components
    private List<ZipComponent> components;

    // This is the constructor that takes a File object as a parameter
    public ZipFileComposite(File file) throws IOException {
        // Create a ZipFile object from the File object
        this.zipFile = new ZipFile(file);
        // Initialize the list of child components
        this.components = new ArrayList<>();
        // Iterate over the entries of the zip file
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            // Get the next entry
            ZipEntry entry = entries.nextElement();
            System.out.println("ENTRYYY " + entry.getName());
            // Unzip the entry into a file
            try (InputStream is = zipFile.getInputStream(entry)) {
                File entryFile = unzipEntry(entry, is, "src\\main\\java\\com\\example\\StudentFiles");
                // rest of your code
            
            
            // Check if the entry is another zip file
            if (entry.getName().endsWith(".zip")) {
                // Create a ZipFileComposite object from the file and add it to the list of child components
                try (ZipFileComposite zfc = new ZipFileComposite(entryFile)) {
                    components.add(zfc);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // Delete the temporary file
                    // entryFile.delete();
                }
                
            } else {
                // Create a ZipEntryLeaf object from the entry and the file and add it to the list of child components
                if (entry.getName().endsWith(".java"))
                {
                    try (FileInputStream fis = new FileInputStream(entryFile)) {
                        components.add(new ZipEntryLeaf(entry, fis));
                    }
                    
                }
                    
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        }
    }

    // This is a private static method that unzips a zip entry into a file
    private static File unzipEntry(ZipEntry entry, InputStream input, String outputFolder) throws IOException {
        // Create a file in the destination directory with the same name as the zip entry
        File file = new File("src\\main\\java\\com\\example\\StudentFiles", entry.getName());
        // Check if the entry is a directory
        if (entry.isDirectory()) {
            // Create the directory
            file.mkdirs();
        } else {
            // Create the parent directories if they do not exist
            file.getParentFile().mkdirs();
            // Create an output stream to write the file
            OutputStream output = new FileOutputStream(file);
            // Copy the input stream to the output stream
 
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            // Close the output stream
            output.close();
        }
        // Return the file
        return file;
    }

    // The rest of the ZipFileComposite class remains the same as before



    @Override
    public void printInfo() {
        // Print the name and size of the zip file
        System.out.println("Outer file name: " + zipFile.getName());
        // Print the info of the child components
        for (ZipComponent component : components) {
            component.printInfo();
        }
    }

    @Override
    public InputStream getInputStream() throws IOException {
        // Return the input stream of the zip file
        return Files.newInputStream(Path.of(zipFile.getName()));
    }

    @Override
    public void close() throws Exception {
        if (zipFile != null) {
            zipFile.close();
        }
    }

    // @Override
    public void add(ZipComponent component) {
        // Add a child component to the list of child components
        components.add(component);
    }

    // @Override
    public void remove(ZipComponent component) {
        // Remove a child component from the list of child components
        components.remove(component);
    }

    public void removeAll()
    {
        for (ZipComponent component : components) {
            if (component instanceof ZipFileComposite)
                ((ZipFileComposite)component).removeAll();
        }
        components.clear();
    }
    

    // @Override
    public ZipComponent getChild(int index) {
        // Return a child component at a given index
        return components.get(index);
    }

    public List<ZipComponent> getComponents(){
        return this.components;
    }

    public String getPath()
    {
        return this.zipFile.getName();
    }

    public Path copySubmission(ZipComponent w)
    {
        Path source = Paths.get(w.getPath());
        // Create a Path object for the target file
        // Use a different name or append a suffix to the source file name
        System.out.println(w.getPath());

        int index = w.getPath().lastIndexOf("\\");
        // Get the substring after the last \ character
        String output = w.getPath().substring(index + 1);
        Path target = Paths.get("src\\main\\java\\com\\example\\StudentFile\\" + output);
        // Copy the source file to the target file, replacing the existing file if any

        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            
            System.out.println("File copied successfully");
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
        }

        return target;
    }
}