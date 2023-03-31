package Entities;

import java.time.LocalDate;
import java.util.List;

public class Student extends Person implements Comparable<Student> {
    private int studentId;
    private List<Subject> subjects;
    private StudyProgram studyProgram;
    private Group group;
    private University college;
    private Series series;

    public Student(){}

    public Student(String firstName, String lastName, String email, Address address, LocalDate birthDate, int studentId, List<Subject> subjects, StudyProgram studyProgram, Group group, University college, Series series) {
        super(firstName, lastName, email, address, birthDate);
        this.studentId = studentId;
        this.subjects = subjects;
        this.studyProgram = studyProgram;
        this.group = group;
        this.college = college;
        this.series = series;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public Student(int studentId, List<Subject> subjects, StudyProgram studyProgram, Group group, University college, Series series) {
        this.studentId = studentId;
        this.subjects = subjects;
        this.studyProgram = studyProgram;
        this.group = group;
        this.college = college;
        this.series = series;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public StudyProgram getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(StudyProgram studyProgram) {
        this.studyProgram = studyProgram;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public University getCollege() {
        return college;
    }

    public void setCollege(University college) {
        this.college = college;
    }

    public float meanAverage(){
        float mean = 0;

        for (Subject s : subjects)
        {
            mean = mean + s.getGrade();
        }

        mean = mean / subjects.size();

        return mean;
    }
    @Override
    public int compareTo(Student s){
        return (int)(this.meanAverage()-s.meanAverage());
    }

    @Override
    public String toString(){
        String s;
        s = "\nFirst name: " + firstName + "\nLast name: " + lastName +
                "\nEmail: " + email + "\nAddress: "  + address.toString() +
                "\nBirth Date: " + birthDate.toString() + "\nStudent ID: " + studentId;
        for (Subject subject: subjects)
        {
            s = s + "\n" + subject.toString();
        }
        s = s + "\nStudy Program: " + studyProgram.getName()
                + "\nCollege: " + college.getName() +"\n";

        return s;
    }
}
