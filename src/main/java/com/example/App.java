package com.example;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!!!!!! :)))))" );
        // ZipFileInput z = new ZipFileInput("Enter zip file path here");
        // z.readFiles();

//         AssignmentSpecPortal assignmentSpecPortal = new AssignmentSpecPortal();
//         assignmentSpecPortal.setVisible(true);

        Composite x = new Composite("ZipFolder.zip");

        // create a zip folder x1 that contains a Java file and a folder with another Java file
    // Composite x1 = new Composite("src\\main\\java\\com\\example\\Avinash_Roopnarine_816029635_A2.zip");
    Leaf java1 = new Leaf("src\\main\\java\\com\\example\\PDFReport.java");


    // x1.add(java1);


    // add the zip folders x1 and x2 to the main zip folder
    x.add(java1);


    // open the main zip folder and extract the Java files only to a folder y
    String folderY = "src\\main\\java\\com\\example\\StudentFiles";
    x.copy(folderY);
  }
    }
