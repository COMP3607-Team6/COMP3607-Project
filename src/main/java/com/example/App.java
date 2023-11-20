package com.example;

public class App 
{
   
    public static void main( String[] args )
    {
        System.out.println( "Hello World!!!!!! :)))))" );

        String dirPath = "files"; // replace with your directory

        JavaFileCopier.copyFiles(dirPath);

        try {
            // Pause for 5 seconds
            Thread.sleep (5000);
        } catch (InterruptedException e) {
            // Handle the interruption
            e.printStackTrace ();
        }

        Delete.deleteFilesInFolder("src\\main\\java\\com\\example\\StudentFile");

    }

    








}

