package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ClassReport implements PDFReport{


    //attributes for Class Report
    private int numStudent;
    private StringBuilder assertionResults;
    private int totalMarks;
    private float classAverage;
    private int classTotal;

    public ClassReport(){
        this.assertionResults = new StringBuilder();
        this.totalMarks = 0;
        this.numStudent = 0;
        this.classAverage = 0;
        this.classTotal = 0;

    }
     


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
        String textFilePath ="src\\main\\java\\com\\example\\TestCases1.txt";
        String pdfPath = "src\\main\\java\\com\\example\\ClassReportOutput.pdf";

        this.numStudent = this.numStudent + 1;

        //System.out.println(pdfPath);

        String assertionResultString;

        for(TestCase test: cases){
            TestResult m = test.getTestMarksObject();

                if(m.getTestPassed()){
                    totalMarks +=m.getTestMarks();
                   
                }
                
         }
        classTotal = classTotal + totalMarks;
        classAverage = (float)(classTotal / (1.0 * numStudent));

         assertionResults.append("Student ID: ").append(StudentID).append("\n");
         assertionResults.append("Total Marks earned: ").append(totalMarks).append("\n");
         assertionResults.append("Total Students : ").append(this.numStudent);
         assertionResults.append("\t Class Average Mark : ").append(this.classAverage).append("\n");

         writeAssertionResultsToFile("src\\main\\java\\com\\example\\TestCases1.txt");

         System.out.println("Count: " + numStudent);

         try {
            generatePDF(textFilePath, pdfPath);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        clear();


    };

    public void writeAssertionResultsToFile(String filePath) {

        removeLastLine(filePath);
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
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

    public void removeLastLine(String filePath) {
          

        try {
            // Step 1: Read the content of the file into a list
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();

            // Step 2: Remove the last element from the list
            if (!lines.isEmpty()) {
                lines.remove(lines.size() - 1);
            }

            // Step 3: Write the updated list back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();

            System.out.println("Last line removed successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clear(){
            this.totalMarks = 0;
            assertionResults.setLength(0);
    }

    
   

    
}

   

  
    

