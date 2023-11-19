package com.example;
import com.itextpdf.kernel.font.PdfFontFactory;
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

public class TestPDF implements PDFReport {
  
  private  PdfWriter writer;
  private PdfDocument pdfDoc;
  private Document document;
  private Table table;
  private int numStudent;
  private int totalMarks;
  private int totalMarksForTests;
  private float classAverage;
  private int classTotal;
  



  public TestPDF(){

    totalMarks = 0;
    classAverage = 0;
    classTotal = 0;
    numStudent = 0;
    totalMarksForTests = 0;


    try {
        writer = new PdfWriter("FancyTable.pdf");
    } catch (FileNotFoundException e) {

        e.printStackTrace();
    }
    
    pdfDoc = new PdfDocument(writer);

    document = new Document(pdfDoc, PageSize.A4);

    table = new Table(new float[]{4, 4, 4});

    table.addCell(createCell("Student ID", TextAlignment.CENTER));
    table.addCell(createCell("Mark", TextAlignment.CENTER));
    table.addCell(createCell("Percentage", TextAlignment.CENTER));


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

         double percentage;

        //System.out.println(StudentID);
        if (assignmentsEnd == false){
            this.numStudent = this.numStudent + 1; 

            for(TestCase test: cases){
                TestResult m = test.getTestMarksObject();

                    if(m.getTestPassed()){
                        totalMarks +=m.getTestMarks();
                    
                    }
                totalMarksForTests +=m.getTestMarks();
                    
            }
            classTotal = classTotal + totalMarks;
            classAverage = (float)(classTotal / (1.0 * numStudent));

            //System.out.println(totalMarks);


            // Adding cells to the table
            table.addCell(createCell(StudentID, TextAlignment.CENTER));
            
            table.addCell(createCell((Integer.toString(totalMarks)), TextAlignment.CENTER));

            percentage = ((float) totalMarks/totalMarksForTests) *  spec.getAssignmentWeighting(); 

            table.addCell(createCell((Double.toString(percentage)), TextAlignment.CENTER));
        }
    
        if(assignmentsEnd == true){

          document.add(table);
          document.close();

        }

        totalMarks = 0;

        totalMarksForTests = 0;

        percentage = 0.00;
    
        System.out.println("PDF updated successfully.");

    }

    // Function to create a cell with content and alignment
    private static Cell createCell(String content, TextAlignment alignment) {
        Cell cell = new Cell().add(new Paragraph(content));
        cell.setTextAlignment(alignment);
        return cell;
    }
}