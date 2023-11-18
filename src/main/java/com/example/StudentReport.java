package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class StudentReport implements PDFReport{

    private StringBuilder assertionResults = new StringBuilder();
    private int totalMarks = 0;

    public void generatePDF(String textFilePath, String outputPDFPath) throws IOException {
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

    public void update(ArrayList<TestCase> cases, String StudentID, AssignmentSpecification spec){
        String textFilePath ="src\\main\\java\\com\\example\\TestCases.txt";
        String pdfPath = "src\\main\\java\\com\\example\\" + StudentID + "Output.pdf";

        System.out.println(pdfPath);

        String assertionResultString;

        for(TestCase test: cases){
            assertionResultString = test.getTestMarksObject().getTestComment();
            TestResult m = test.getTestMarksObject();

                if(m.getTestPassed()){
                    totalMarks +=m.getTestMarks();
                }
                assertionResults.append("Test Case: ").append(assertionResultString).append("\n");
         }
         assertionResults.append("Total Marks earned: ").append(totalMarks).append("\n");

         writeAssertionResultsToFile("src\\main\\java\\com\\example\\TestCases.txt");

         try {
            generatePDF(textFilePath, pdfPath);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        clear();



    };

    public void writeAssertionResultsToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Get the assertion results and write them to the file
            String results = getAssertionResults();
            writer.write(results);

            System.out.println("Assertion results have been written to the file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAssertionResults() {
        return assertionResults.toString();
    }

    public void clear(){
        this.totalMarks = 0;
        assertionResults.setLength(0);
}


    
    

    // public static void main(String[] args) {
    //     try {
    //         generatePDF("src\\main\\java\\com\\example\\TestCases.txt", "src\\main\\java\\com\\example\\Output.pdf");
    //         System.out.println("PDF generated successfully.");
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
}