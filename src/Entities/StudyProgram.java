package Entities;

public class StudyProgram {
    public String name;
    public String levelOfStudy;

    public StudyProgram(){}

    public StudyProgram(String name, String levelOfStudy) {
        this.name = name;
        this.levelOfStudy = levelOfStudy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevelOfStudy() {
        return levelOfStudy;
    }

    public void setLevelOfStudy(String levelOfStudy) {
        this.levelOfStudy = levelOfStudy;
    }
}
