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


// The Composite class represents a zip file that can contain other zip files or Java files
public class Composite implements Component {
  private String name; // the name of the zip file
  private List<Component> components; // the list of components inside the zip file

  public Composite(String name) {
    this.name = name;
    this.components = new ArrayList<>();
  }

  // add a component to the zip file
  public void add(Component component) {
    this.components.add(component);
  }

  // remove a component from the zip file
  public void remove(Component component) {
    this.components.remove(component);
  }

  // copy the zip file and its components to a destination folder
  public void copy(Object destination) {
    // create a new zip file in the destination folder
    try{
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(destination + "/" + name));
        // iterate over the components and copy them to the new zip file
        for (Component component : components) {
        // component.copy(zos);
        }
        // close the zip output stream
        zos.close();
    }
    catch (FileNotFoundException e)
    {
        System.out.println (e.getMessage());
    }
    catch (IOException e)
    {
        System.out.println (e.getMessage());
    }
  }

  // save a PDF file to the zip file
  public void savePDF(String pdfName) {
    // create a new zip entry for the PDF file
    ZipEntry ze = new ZipEntry(pdfName);
    // open the zip file for writing
    try
        {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(name));
            // put the zip entry to the zip output stream
            zos.putNextEntry(ze);
            // write the PDF file content to the zip output stream
            // ...
            // close the zip entry and the zip output stream
            zos.closeEntry();
            zos.close();
        }
    catch ( FileNotFoundException e )
    {
        System.out.println(e.getMessage());
    }
    catch (IOException e)
    {
        System.out.println( e.getMessage());
    }
  }

  // delete the zip file and its components
  public void delete() {
    // iterate over the components and delete them
    for (Component component : components) {
      component.delete();
    }
    // delete the zip file
    File file = new File(name);
    file.delete();
  }

  public static void main(String[] args) {

try {
//     // create a ZipFile object for the zip folder
// ZipFile zipFolder = new ZipFile("ZipFolder.zip");

// // get an enumeration of the entries in the zip folder
// Enumeration<? extends ZipEntry> entries = zipFolder.entries();

// // iterate over the entries
// while (entries.hasMoreElements()) {
//   // get the next entry
//   ZipEntry entry = entries.nextElement();

//   // check if the entry is a folder or a file
//   if (entry.isDirectory()) {
//     // get an input stream of the folder
//     InputStream is = zipFolder.getInputStream(entry);

//     // create a new ZipFile object from the input stream
//     ZipFile zipFile = new ZipFile(is);
    

//     // get an enumeration of the entries in the zip file
//     Enumeration<? extends ZipEntry> zipEntries = zipFile.entries();

//     // iterate over the zip entries
//     while (zipEntries.hasMoreElements()) {
//       // get the next zip entry
//       ZipEntry zipEntry = zipEntries.nextElement();

//       // check if the zip entry is a file
//       if (!zipEntry.isDirectory()) {
//         // get the name of the file
//         String fileName = zipEntry.getName();

//         // check if the file is a Java file
//         if (fileName.endsWith(".java")) {
//           // copy the Java file to the specified folder
//           // you can use your existing copy method from the Component interface
//           // or use any other method you prefer
//           Component component = new Leaf(fileName);
//           component.copy("specifiedFolder");
//         }
//       }
//     }
//   } else {
//     // get the name of the file
//     String fileName = entry.getName();

//     // check if the file is a Java file
//     if (fileName.endsWith(".java")) {
//       // copy the Java file to the specified folder
//       // you can use your existing copy method from the Component interface
//       // or use any other method you prefer
//       Component component = new Leaf(fileName);
//       component.copy("src\\main\\java\\com\\example\\StudentFiles");
//     }
//   }
// }



  }

  catch (Exception e)
  {
    System.out.println( e.getMessage());
  }
}
}