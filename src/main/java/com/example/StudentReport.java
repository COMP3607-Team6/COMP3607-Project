package com.example;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

public class StudentReport implements PDFReport {
  
    private  PdfWriter writer;
    private PdfDocument pdfDoc;
    private Document document;
    private Table table;
    private int totalMarks;
    private int totalMarksForTests;
    private AssignmentSpecification spec;
    
    public StudentReport(AssignmentSpecification spec){
        this.spec = spec;
        totalMarks = 0;
        totalMarksForTests = 0;
    }

    public void update(ArrayList<TestCase> cases, String StudentID,boolean assignmentsEnd){

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

        document.add(new Paragraph(String.format("%s %s", spec.getCourseCode(), spec.getTitle())).setTextAlignment(TextAlignment.CENTER).setItalic().setBold());
        document.add(new Paragraph(String.format("Weighting : %.2f", spec. getAssignmentWeighting()) + "%").setTextAlignment(TextAlignment.CENTER).setItalic().setBold());

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

        }

    }

    // Function to create a cell with content and alignment
    private static Cell createCell(String content, TextAlignment alignment) {
        Cell cell = new Cell().add(new Paragraph(content));
        cell.setTextAlignment(alignment);
        return cell;
    }
}