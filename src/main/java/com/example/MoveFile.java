package com.example;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class MoveFile {
    public static void main(String[] args) {
        // Define the source folder and destination folder within the project
        String sourceFolderPath = "C:\\Users\\Avinash Roopnarine\\Desktop\\OOP 2\\COMP3607-Project\\target\\classes\\com\\example\\Avinash_Roopnarine_816029635_A2"; // Update with the actual source folder path
        String destinationPath = "C:\\Users\\Avinash Roopnarine\\Desktop\\OOP 2\\COMP3607-Project\\src\\main\\java\\com\\example"; // Update with the desired destination path

        try {
            // Resolve the full paths
            Path sourceDirectory = Paths.get(sourceFolderPath).toAbsolutePath();
            Path destinationDirectory = Paths.get(destinationPath).toAbsolutePath();

            // Check if the source folder exists
            if (Files.exists(sourceDirectory) && Files.isDirectory(sourceDirectory)) {
                // Create the destination directory if it doesn't exist
                if (!Files.exists(destinationDirectory)) {
                    Files.createDirectories(destinationDirectory);
                }

                // List all files in the source directory
                try (Stream<Path> files = Files.list(sourceDirectory)) {
                    files.forEach(file -> {
                        try {
                            // Define the destination file path
                            Path destinationFile = destinationDirectory.resolve(file.getFileName());

                            // Move each file to the destination
                            Files.move(file, destinationFile, StandardCopyOption.REPLACE_EXISTING);

                            System.out.println("Moved: " + file.getFileName());
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.err.println("Error occurred while moving a file.");
                        }
                    });
                }

                System.out.println("All files moved successfully.");
            } else {
                System.err.println("Source folder does not exist or is not a directory.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error occurred while moving files.");
        }
    }
}