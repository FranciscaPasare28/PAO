package Entities;

public class Group {
    private String studyProgram;
    private int number;

    public Group(){}
    public Group(String studyProgram, int number) {
        this.studyProgram = studyProgram;
        this.number = number;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
