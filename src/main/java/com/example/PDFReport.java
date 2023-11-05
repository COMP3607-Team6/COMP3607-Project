package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.commons.io.FileUtils;

public class PDFReport {
    public static void generatePDF(String textFilePath, String outputPDFPath) throws IOException {
        File textFile = new File(textFilePath);

        // Create a PDF document
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Create a content stream for the PDF
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.setFont(PDType1Font.COURIER, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(5, 700);

            // Read and process the text file while maintaining the format
            try (BufferedReader reader = new BufferedReader(new FileReader(textFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Sanitize the line by removing control characters and extra whitespace
                    line = line.replaceAll("[\\p{Cc}\\p{Cf}]+", " ").trim();

                    contentStream.showText(line);
                    contentStream.newLineAtOffset(0, -15); // Adjust the vertical position as needed
                }
            }

            contentStream.endText();
        }

        // Save the PDF document
        document.save(outputPDFPath);
        document.close();
    }
    
    

    public static void main(String[] args) {
        try {
            generatePDF("C:\\Users\\Avinash Roopnarine\\Desktop\\OOP 2\\COMP3607-Project\\src\\main\\java\\com\\example\\TestCases.txt", "C:\\Users\\Avinash Roopnarine\\Desktop\\OOP 2\\COMP3607-Project\\src\\main\\java\\com\\example\\Output.pdf");
            System.out.println("PDF generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}