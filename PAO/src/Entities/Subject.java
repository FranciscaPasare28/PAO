package Entities;

import java.io.Serializable;

public class Subject implements Serializable
{
    private int studentId;
    //name
    private String subjectName;
    //grade
    private int mark;

    public Subject() { }

    public Subject(int studentId, String subjectName, int mark)
    {
        this.studentId = studentId;
        this.subjectName = subjectName;
        this.mark = mark;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getSubjectName()
    {
        return subjectName;
    }

    public void setSubjectName(String subjectName)
    {
        this.subjectName = subjectName;
    }

    public int getMark()
    {
        return mark;
    }

    public void setMark(int mark)
    {
        this.mark = mark;
    }

    @Override
    public String toString()
    {
        return "Student ID: " + studentId + "\nSubject Name: " + subjectName + "\nMark: " + mark + "\n";
    }
}
