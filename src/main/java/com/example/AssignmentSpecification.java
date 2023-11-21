package com.example;

/**
* Stores assignment specification provided by user of the system
*/
public class AssignmentSpecification {

    private String courseCode;
    private String title;
    private String description;
    private String folderPath;
    private String deadlineDate;
    private float assignmentWeighting;
    private int assignmentNumber;

    /**
    * Assignment Specification with Assignment number
    */
    public AssignmentSpecification(String courseCode, String title, String description,String folderPath, String deadlineDate, float assignmentWeighting, int assignmentNumber) {

        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.folderPath  = folderPath;
        this.deadlineDate = deadlineDate;
        this.assignmentWeighting = assignmentWeighting;
        this.assignmentNumber = assignmentNumber;
    }
    
    /**
    * Assignment Specification without Assignment number
    */
    public AssignmentSpecification(String courseCode, String title, String description,String folderPath, String deadlineDate, float assignmentWeighting) {

        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.folderPath  = folderPath;
        this.deadlineDate = deadlineDate;
        this.assignmentWeighting = assignmentWeighting;
    }
    

    public int getAssignmentNumber() {
        return this.assignmentNumber;
    }

    public void setAssignmentNumber(int assignmentNumber) {
        this.assignmentNumber = assignmentNumber;
    }

    public String getCourseCode() {
        return this.courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFolderPath() {
        return this.folderPath;
    }
    public float getAssignmentWeighting() {
        return this.assignmentWeighting;
    }

    public void setFolderPath(String filePath) {
        this.folderPath = filePath;
    }

    public String getDeadlineDate() {
        return this.deadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public void setAssignmentWeighting(float assignmentWeighting){
        this.assignmentWeighting = assignmentWeighting;
    }


    @Override
    public String toString() {
        return "{" +
            " courseCode='" + getCourseCode() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", folderPath='" + getFolderPath() + "'" +
            ", deadlineDate='" + getDeadlineDate() + "'" +
            ", assignmentWeighting='" + getAssignmentWeighting() + "'" +
            "}";
    }

        
}
