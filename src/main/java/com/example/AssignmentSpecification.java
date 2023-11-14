package com.example;

public class AssignmentSpecification {

    private String courseCode;
    private String title;
    private String description;
    private String folderPath;
    private String deadlineDate;

    public AssignmentSpecification(String courseCode, String title, String description,String folderPath, String deadlineDate) {

        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.folderPath  = folderPath;
        this.deadlineDate = deadlineDate;
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

    public void setFolderPath(String filePath) {
        this.folderPath = filePath;
    }

    public String getDeadlineDate() {
        return this.deadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }
        
}
