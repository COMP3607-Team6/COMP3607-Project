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
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

public class ClassReport implements PDFReport {
  
  private  PdfWriter writer;
  private PdfDocument pdfDoc;
  private Document document;
  private Table table;
  private int numStudent;
  private int totalMarks;
  private int totalMarksForTests;
  private float classAverage;
  private int classTotal;
  private AssignmentSpecification spec;
  



  public ClassReport(AssignmentSpecification assignmentSpec){

        this.spec = assignmentSpec;
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

        document.add(new Paragraph(String.format("%s %s", spec.getCourseCode(), spec.getTitle())).setTextAlignment(TextAlignment.CENTER).setItalic().setBold());
        document.add(new Paragraph(String.format("Weighting : %.2f", spec. getAssignmentWeighting()) + "%").setTextAlignment(TextAlignment.CENTER).setItalic().setBold());

        table.addCell(createCell("Student ID", TextAlignment.CENTER));
        table.addCell(createCell("Mark", TextAlignment.CENTER));
        table.addCell(createCell("Percentage", TextAlignment.CENTER));

  }


    public void update(ArrayList<TestCase> cases, String StudentID, boolean assignmentsEnd, String submission_location){

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

            // Adding cells to the table
            table.addCell(createCell(StudentID, TextAlignment.CENTER));
            
            table.addCell(createCell((Integer.toString(totalMarks)), TextAlignment.CENTER));

            percentage = ((float) totalMarks/totalMarksForTests) *  spec.getAssignmentWeighting(); 

            table.addCell(createCell((Double.toString(percentage)), TextAlignment.CENTER));
        }
    
        if(assignmentsEnd == true){
          table.setHorizontalAlignment(HorizontalAlignment.CENTER);
          document.add(table);
          document.close();
          SystemNotification endNotification = new SystemNotification("Assignment Files have completed processing!! Check assignment folder for results!!");

        }

        totalMarks = 0;

        totalMarksForTests = 0;

        percentage = 0.00;
    
        System.out.println("PDF updated successfully.");
        // return "Need to update return in ClassReport.java";

    }

    // Function to create a cell with content and alignment
    private static Cell createCell(String content, TextAlignment alignment) {
        Cell cell = new Cell().add(new Paragraph(content));
        cell.setTextAlignment(alignment);
        return cell;
    }
}