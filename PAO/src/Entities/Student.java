package Entities;

import Entities.StudyProgram;

import java.time.LocalDate;

public class Student extends Person
{
    private int studentId;
    private StudyProgram studyProgram;
    private Group group;
    private University university;

    private Series series;

    public Student() { }

    public Student(int studentId, String firstName, String lastName, String email, Address address,
                   LocalDate birthDate, StudyProgram studyProgram, Group group, University university, Series series)
    {
        super(firstName, lastName, email, address, birthDate);
        this.studentId = studentId;
        this.studyProgram = studyProgram;
        this.group = group;
        this.university = university;
        this.series = series;
    }

    public int getStudentId()
    {
        return studentId;
    }

    public void setStudentId(int studentId)
    {
        this.studentId = studentId;
    }



    public Group getGroup()
    {
        return group;
    }

    public void setGroup(Group group)
    {
        this.group = group;
    }

    public StudyProgram getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(StudyProgram studyProgram) {
        this.studyProgram = studyProgram;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

//    public String toString()
//    {
//        String s;
//        s = "\nStudent ID: " + studentId + "\nFirst name: " + firstName + "\nLast name: " + lastName +
//                "\nEmail: " + email + "\nAddress: " + address.toString() +
//                "\nBirth Date: " + birthDate.toString();
//        s = s + "\nStudy program Name: " + studyProgram.getName() + "\nGroup number: " + group.getNumber() + "\n Series number: " + series.getNumber()
//                + "\nUniversity name: " + university.getName() +"\n";
//
//        return s;
//    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\nStudent ID: ").append(studentId)
                .append("\nFirst name: ").append(firstName)
                .append("\nLast name: ").append(lastName);

        if (email != null) {
            s.append("\nEmail: ").append(email);
        }

        if (address != null) {
            s.append("\nAddress: ").append(address.toString());
        }

        if (birthDate != null) {
            s.append("\nBirth Date: ").append(birthDate.toString());
        }

        if (studyProgram != null) {
            s.append("\nStudy program Name: ").append(studyProgram.getName());
        }

        if (group != null) {
            s.append("\nGroup number: ").append(group.getNumber());
        }

        if (series != null) {
            s.append("\nSeries number: ").append(series.getNumber());
        }

        if (university != null) {
            s.append("\nUniversity name: ").append(university.getName());
        }

        s.append("\n");

        return s.toString();
    }

}
