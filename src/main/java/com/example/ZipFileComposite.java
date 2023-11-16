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
import java.io.File;
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
            // Check if the entry is another zip file
            if (entry.getName().endsWith(".zip")) {
                ZipFileReader z = new ZipFileReader();

                String path = z.unzip(entry.getName(), "src\\main\\java\\com\\example\\StudentFiles" );
                    
                Path tempFile = Files.createTempFile(null, null);
                Files.copy(zipFile.getInputStream(entry), tempFile, StandardCopyOption.REPLACE_EXISTING);
                // Create a ZipFileComposite object from the temporary file and add it to the list of child components
                // if (path.length() != 0)
                //     components.add(new ZipFileComposite(Paths.get(path).toFile()));
                System.out.println("TEMP : " + tempFile.toString());
                components.add(new ZipFileComposite(tempFile.toFile()));
                // components.add(new ZipFileComposite(zipFile));
            } else {
                // Create a ZipEntryLeaf object from the entry and add it to the list of child components
                if (entry.getName().endsWith(".java"))
                    components.add(new ZipEntryLeaf(entry, zipFile.getInputStream(entry)));
            }
        }
    }

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
}