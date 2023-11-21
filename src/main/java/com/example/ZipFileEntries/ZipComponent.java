package com.example.ZipFileEntries;

import java.io.InputStream;
import java.io.IOException;

/*
 * This interface specifies a contract that all ZipFileComposite and ZipEntryLeaf objects must follow
 * This is apart of the Composite Design Pattern
 */
public interface ZipComponent {

    void printInfo();
    InputStream getInputStream() throws IOException;
    String getPath();

}
