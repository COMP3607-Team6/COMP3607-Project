package com.example;

import java.io.InputStream;
import java.io.IOException;

public interface ZipComponent {
    // This method prints the name of the component
    void printInfo();
    // This method returns the input stream of the component
    InputStream getInputStream() throws IOException;
    String getPath();

}
