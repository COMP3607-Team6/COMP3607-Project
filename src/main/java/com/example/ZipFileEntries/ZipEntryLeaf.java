package com.example.ZipFileEntries;
import java.util.zip.ZipEntry;
import java.io.InputStream;

/*
 * This is the ZipEntryLeaf class that represents a zip entry
 * Apart of the Composite Design Pattern
 */
public class ZipEntryLeaf implements ZipComponent {
    // This is the ZipEntry object that wraps the actual zip entry
    private ZipEntry zipEntry;
    // This is the input stream of the zip entry
    private InputStream inputStream;
    public String storedFilePath;
    public String rel_path;


    /*
     * // This is the constructor that takes a ZipEntry object and an input stream as parameters
     */
    public ZipEntryLeaf(ZipEntry zipEntry, InputStream inputStream) {
        // Assign the ZipEntry object and the input stream to the fields
        this.zipEntry = zipEntry;
        this.inputStream = inputStream;
        storedFilePath = "src\\main\\java\\com\\example\\StudentFiles\\" + (zipEntry.getName()).toString().replace("/", "\\");
        rel_path = (zipEntry.getName()).toString().replace("/", "\\");;
    }

    @Override
    public void printInfo() {
        System.out.println("Java entry name Leaf: " + storedFilePath);
    }

    @Override
    public InputStream getInputStream() {
        // Return the input stream of the zip entry
        return inputStream;
    }

    @Override
    public String getPath(){
        return this.storedFilePath;
    }

    public String get_rel_path()
    {
        return this.rel_path;
    }

}