package Entities;

public class Group {
    private int number;
    StudyProgram studyProgram;
    public Group(){}

    public Group(int number, StudyProgram studyProgram) {
        this.number = number;
        this.studyProgram = studyProgram;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public StudyProgram getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(StudyProgram studyProgram) {
        this.studyProgram = studyProgram;
    }
}
