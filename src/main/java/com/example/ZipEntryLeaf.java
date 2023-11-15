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
import java.io.InputStream;

// This is the ZipEntryLeaf class that represents a zip entry
public class ZipEntryLeaf implements ZipComponent {
    // This is the ZipEntry object that wraps the actual zip entry
    private ZipEntry zipEntry;
    // This is the input stream of the zip entry
    private InputStream inputStream;

    // This is the constructor that takes a ZipEntry object and an input stream as parameters
    public ZipEntryLeaf(ZipEntry zipEntry, InputStream inputStream) {
        // Assign the ZipEntry object and the input stream to the fields
        this.zipEntry = zipEntry;
        this.inputStream = inputStream;
    }

    @Override
    public void printInfo() {
        // Print the name and size of the zip entry
        System.out.println("Zip entry name: " + zipEntry.getName());
        System.out.println("Zip entry size: " + zipEntry.getSize() + " bytes");
    }

    @Override
    public InputStream getInputStream() {
        // Return the input stream of the zip entry
        return inputStream;
    }

    @Override
    public void add(ZipComponent component) {
        // Do nothing, as a leaf has no children
    }

    @Override
    public void remove(ZipComponent component) {
        // Do nothing, as a leaf has no children
    }

    @Override
    public ZipComponent getChild(int index) {
        // Return null, as a leaf has no children
        return null;
    }
}