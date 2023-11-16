package com.example;

import java.util.ArrayList;

import com.example.AssignmentSpecificationPortal.AssignmentSpecPortal;
import com.example.AssignmentSpecificationPortal.ClassInformation;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Hello World!!!!!! :)))))");

        // ZipFileInput z = new ZipFileInput("Enter zip file path here");
        // z.readFiles();

        ArrayList<ClassInformation> classes = new ArrayList<ClassInformation>();
        
        AssignmentSpecPortal assignmentSpecPortal = new AssignmentSpecPortal(classes);
        // assignmentSpecPortal.setVisible(true);
    }
}