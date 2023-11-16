package com.example;

import java.io.InputStream;
import java.io.IOException;

public interface ZipComponent {
    // This method prints the name of the component
    void printInfo();
    // This method returns the input stream of the component
    InputStream getInputStream() throws IOException;

    // // This method adds a child component to the component
    // void add(ZipComponent component);
    // // This method removes a child component from the component
    // void remove(ZipComponent component);
    // // This method returns a child component at a given index
    // ZipComponent getChild(int index);
}
