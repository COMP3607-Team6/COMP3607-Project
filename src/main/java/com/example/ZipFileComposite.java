package com.example;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipOutputStream;
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
import com.example.ZipComponent;


// This is the ZipFileComposite class that represents a zip file
public class ZipFileComposite implements ZipComponent {
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
            // Unzip the entry into a file
            File entryFile = unzipEntry(entry, zipFile.getInputStream(entry), "src\\main\\java\\com\\example\\StudentFiles" );
            // Check if the entry is another zip file
            if (entry.getName().endsWith(".zip")) {
                // Create a ZipFileComposite object from the file and add it to the list of child components
                components.add(new ZipFileComposite(entryFile));
                // Delete the temporary file
                entryFile.delete();
            } else {
                // Create a ZipEntryLeaf object from the entry and the file and add it to the list of child components
                if (entry.getName().endsWith(".java"))
                    components.add(new ZipEntryLeaf(entry, new FileInputStream(entryFile)));
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
}