package Beans;

public class EnrollBean {
    private String courseID;
    private String title;
    private String grade;

    // Constructors
    public EnrollBean() {}

    // Getters and Setters
    public String getCourseID() { return courseID; }
    public void setCourseID(String courseID) { this.courseID = courseID; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}