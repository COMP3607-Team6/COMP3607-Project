package com.example.ZipFileEntries;

/*
 * This interface specifies a contract for subclasses to unzip files
 */
public interface Unzip {
    public String unzip(String zipFilePath, String outputFolder);
}
