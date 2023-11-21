package com.example;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class App 

{
   
    
    /** 
     * @param args
     */
    public static void main( String[] args )
    {
        // System.out.println( "Hello World!!!!!! :)))))" );

        // String dirPath = "src\\main\\java\\com\\example\\TestAssignment"; // replace with your directory

        // JavaFileCopier.copyFiles(dirPath);

        // try {
        //     // Pause for 5 seconds
        //     Thread.sleep (5000);
        // } catch (InterruptedException e) {
        //     // Handle the interruption
        //     e.printStackTrace ();
        // }

        // try{
        
        //     FileToZipCopier.copyFile(new File ("src\\main\\java\\com\\example\\assignment.zip"), new File ("1233456.pdf"));
        // }
        // catch (Exception e)
        // {
        //     e.printStackTrace();
        // }

        // Delete.deleteFilesInFolder("src\\main\\java\\com\\example\\StudentFile");
        // Delete.deleteFolder("src\\main\\java\\com\\example\\StudentFiles");

        Map<String, String> result = getNameFromSubmission("wjvwjkevwjevqwdqwfqw111.zip", "comp");
        System.out.println("Name: " + result.toString());
      

    }

    public static Map<String,String> getNameFromSubmission(String str, String assignmentName) {
       // Remove everything before the last '/'
            int index = str.lastIndexOf('/');
            if (index != -1) {
                str = str.substring(index + 1);
            }

            // Remove the assignment name
            str = str.replaceAll("(?i)" + assignmentName, "");

            // Remove any 'A' followed by a digit
            str = str.replaceAll("A\\d", "");

            // Remove the file extension
            index = str.lastIndexOf('.');
            if (index != -1) {
                str = str.substring(0, index);
            }

            // Split the string by '_'
            String[] parts = str.split("_");

            // Initialize name and number
            String name = "";
            String number = "";

            for (String part : parts) {
                // If part is numeric and has 6 or more digits, it's the number
                if (part.matches("\\d{6,}")) {
                    number = part;
                }
                // Otherwise, if it's not the assignment name, it's part of the name
                else if (!part.equalsIgnoreCase(assignmentName)) {
                    name += part;

                }
            }

            name = str.replaceAll("(?i)[0-9_]*", "");

            // Create a map and put the name and number into it
            Map<String, String> result = new HashMap<>();
            result.put("name", name);
            result.put("number", number);

            return result;
            
}
    
}








