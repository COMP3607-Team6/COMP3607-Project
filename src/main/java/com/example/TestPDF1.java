package com.example;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TestPDF1 implements PDFReport {
  
  private  PdfWriter writer;
  private PdfDocument pdfDoc;
  private Document document;
  private Table table;
  private int totalMarks;
  private int totalMarksForTests;
 

 




  public TestPDF1(){
     totalMarks = 0;
     totalMarksForTests = 0;
  }
  public static void main(String[] args) {
        // try {
        //     // Create a PdfWriter
        //     PdfWriter writer = new PdfWriter("FancyTable.pdf");
        //     int studentId = 8150303;

        //     // Create a PdfDocument
        //     PdfDocument pdfDoc = new PdfDocument(writer);

        //     // Create a Document
        //     Document document = new Document(pdfDoc, PageSize.A4);

        //     // Creating a table
        //     Table table = new Table(new float[]{4, 4, 4}); // 3 columns with equal width

        //     // Adding cells to the table
        //     table.addCell(createCell("Student ID", TextAlignment.CENTER));
        //     table.addCell(createCell("Mark", TextAlignment.CENTER));
        //     table.addCell(createCell("Percentage", TextAlignment.CENTER));

        //     // Adding content to the table
        //     for (int i = 1; i <= 16; i++) {
        //         table.addCell(createCell("Row " + studentId + ", Col 1", TextAlignment.LEFT));
        //         table.addCell(createCell("Row " + i + ", Col 2", TextAlignment.LEFT));
        //         table.addCell(createCell("Row " + i + ", Col 3", TextAlignment.LEFT));
        //     }

        //     // Write text in a specific row (let's say row 5)
        //     int rowIndexToWrite = 5;
        //     for (int i = 0; i < 3; i++) {
        //         // Replace the content in the cells of the specified row
        //         Cell cell = table.getCell(rowIndexToWrite, i);
        //         cell.add(new Paragraph("Updated Row " + rowIndexToWrite + ", Col " + (i + 1)));
        //     }

        //     // Add the table to the document
        //     document.add(table);

        //     // Close the document
        //     document.close();

        //     System.out.println("PDF created successfully.");

        // } catch (FileNotFoundException e) {
        //     e.printStackTrace();
        // }
    }

    public void update(ArrayList<TestCase> cases, String StudentID, AssignmentSpecification spec, boolean assignmentsEnd){

        int countTestcases = 0;

        if(StudentID != null){

         try {
            writer = new PdfWriter(StudentID +".pdf");
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }


        pdfDoc = new PdfDocument(writer);

        document = new Document(pdfDoc, PageSize.A4);

        table = new Table(new float[]{4, 4, 4, 4, 4});

        table.addCell(createCell("Test Case Number", TextAlignment.CENTER));
        table.addCell(createCell("Test Case Name", TextAlignment.CENTER));
        table.addCell(createCell("Test Case Mark", TextAlignment.CENTER));
        table.addCell(createCell("Pass/Fail", TextAlignment.CENTER));
        table.addCell(createCell("Comment", TextAlignment.CENTER));

        for(TestCase test: cases){
           
            TestResult m = test.getTestMarksObject();
                countTestcases+= 1; 
                table.addCell(createCell(Integer.toString(countTestcases), TextAlignment.CENTER));
                table.addCell(createCell(test.getTestName(), TextAlignment.CENTER));
                table.addCell(createCell(Integer.toString(m.getTestMarks()), TextAlignment.CENTER));
                if(m.getTestPassed()){
                    table.addCell(createCell("Pass", TextAlignment.CENTER));
                    totalMarks +=m.getTestMarks();
                }
                else{
                  table.addCell(createCell("Fail", TextAlignment.CENTER));
                }
                table.addCell(createCell(m.getTestComment(), TextAlignment.CENTER));
                totalMarksForTests +=m.getTestMarks();
         }

         double percentage = ((float) totalMarks/totalMarksForTests) *  spec.getAssignmentWeighting();      

         document.add(table);

         document.add(new Paragraph(String.format("Total Marks: %d", totalMarks)).setTextAlignment(TextAlignment.LEFT).setBold());
         document.add(new Paragraph(String.format("Assignment Weighted Percentage: %.2f", percentage) + "%").setTextAlignment(TextAlignment.LEFT).setBold());

         document.close();


         totalMarks = 0;

         totalMarksForTests =0;

         percentage = 0.00;
    

        //System.out.println(StudentID);

        System.out.println("Student PDF has been created successfully.");

        }

    }

    // Function to create a cell with content and alignment
    private static Cell createCell(String content, TextAlignment alignment) {
        Cell cell = new Cell().add(new Paragraph(content));
        cell.setTextAlignment(alignment);
        return cell;
    }
}