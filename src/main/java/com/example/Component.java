package com.example;

public interface Component {
        // public void copy(Object destination); // copy the component to a destination folder
        public void savePDF(String pdfName); // save a PDF file to the component
        public void delete(); // delete the component
}
