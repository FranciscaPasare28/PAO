package Services;

import Database.DatabaseConfiguration;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import Entities.*;
import Repository.*;

public class Service
{
    private static Service instance;

    public static List<Subject> subjects = new ArrayList<>();
    public static List<StudyProgram> studyprograms = new ArrayList<>();
    public static List<Group> groups = new ArrayList<>();
    public static List<University> universities = new ArrayList<>();
    public static List<Student> students = new ArrayList<>();
    public static List<Series> series = new ArrayList<>();


    StudyProgramRepository studyProgramRepository = StudyProgramRepository.getInstance();
    GroupRepository groupRepository = GroupRepository.getInstance();
    SeriesRepository seriesRepository = SeriesRepository.getInstance();
    UniversityRepository universityRepository = UniversityRepository.getInstance();
    StudentRepository studentRepository = StudentRepository.getInstance();
    SubjectRepository subjectRepository = SubjectRepository.getInstance();

    AuditService auditService = AuditService.getInstance();

    private Service(){}
    public static Service getInstance(){
        if (instance==null){
            instance = new Service();
        }
        return instance;
    }
    public void loadData()
    {
        subjects = ReadWrite.readSubject();
        studyprograms = ReadWrite.readStudyProgram();
        groups = ReadWrite.readGroup();
        universities = ReadWrite.readUniversity();
        students = ReadWrite.readStudent();
        series = ReadWrite.readSeries();

        subjectRepository.addData();
        studyProgramRepository.addData();
        groupRepository.addData();
        universityRepository.addData();
        studentRepository.addData();
        seriesRepository.addData();

        try
        {
            auditService.logAction("load data");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void configureTables()
    {
        subjectRepository.createTable();
        studyProgramRepository.createTable();
        groupRepository.createTable();
        universityRepository.createTable();
        studentRepository.createTable();
        seriesRepository.createTable();

        try
        {
            auditService.logAction("configure tables");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void addSubject()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Student ID: ");
        int studentId;

        while(true)
        {
            try
            {
                studentId = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("ID Student: ");
            }
        }

        System.out.print("Name of the subject: ");
        String subjectName = reader.nextLine().toUpperCase();

        int mark;

        System.out.print("Mark: ");
        while(true)
        {
            try
            {
                mark = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Mark: ");
            }
        }

        subjects.add(new Subject(studentId, subjectName, mark));
        subjectRepository.addSubject(studentId, subjectName, mark);

        try
        {
            auditService.logAction("add subject");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void updateSubject() {
        Scanner reader = new Scanner(System.in);

        System.out.print("Student ID: ");
        int studentId = reader.nextInt();
        reader.nextLine();

        System.out.print("Subject Name: ");
        String name = reader.nextLine();

        boolean check = false;
        for(Subject s: subjects)
            if(s.getStudentId()==studentId && s.getSubjectName().equalsIgnoreCase(name)){
                check=true;
                break;
            }

        if (check) {
            System.out.print("New Mark: ");
            int mark = reader.nextInt();
            subjectRepository.updateSubject(studentId, name, mark);
            System.out.println("Subject updated successfully!");
        } else {
            System.out.println("No existing subject with the given Student ID and Subject Name!");
        }

        try {
            auditService.logAction("update subject");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteSubject() {
        Scanner reader = new Scanner(System.in);

        System.out.print("Student ID: ");
        int studentId = reader.nextInt();
        reader.nextLine();

        boolean check = false;
        for(Subject s: subjects)
            if(s.getStudentId()==studentId ){
                check=true;
                break;
            }

        if (check) {
            subjectRepository.deleteSubject(studentId);
            System.out.println("Subject deleted successfully!");
        } else {
            System.out.println("No existing subject with the given Student ID and Subject Name!");
        }

        try {
            auditService.logAction("delete subject");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printSubjects()
    {
        subjectRepository.displaySubjects();

        try
        {
            auditService.logAction("print subjects");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void addStudyProgram()
    {
        Scanner reader = new Scanner(System.in);

        String studyprogram="";
        String levelofStudy="";
        try {
            System.out.print("The name of the study program: ");
            System.out.println("You can choose one of: CS(Computer Science)/M(Mathematics)/CIT(Computers and information technology)");
            studyprogram = reader.nextLine();

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.print("Level of study: ");
            System.out.println("You can choose one of: BD/MD/DD");
            levelofStudy = reader.nextLine();

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        studyprograms.add(new StudyProgram(studyprogram, levelofStudy));
        studyProgramRepository.addStudyProgram(studyprogram, levelofStudy);

        try{
            auditService.logAction("add studyprogram");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void updateStudyProgram() {
        Scanner reader = new Scanner(System.in);

        System.out.print("Name: ");
        String name = reader.nextLine();

        boolean check = false;
        for (StudyProgram sp: studyprograms) {
            if (sp.getName().equals(name)) {
                check = true;
                break;
            }
        }
        if (check) {
            System.out.print("New Level of Study: ");
            System.out.println("You can choose one of: CS(Computer Science)/M(Mathematics)/CIT(Computers and information technology)");
            String newLevel = reader.nextLine();
            studyProgramRepository.updateStudyProgram(name, newLevel);
            System.out.println( "Level of study was updated successfully.");
        }else{
            System.out.println("Study program with name " + name + "' does not exist.");
        }
        try
        {
            auditService.logAction("update study program");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteStudyProgram() {
        Scanner reader = new Scanner(System.in);

        System.out.print("Name: ");
        String name = reader.nextLine();
        boolean check = false;

        for (StudyProgram sp: studyprograms) {
            if (sp.getName().equals(name)) {
                check = true;
                break;
            }
        }

        if (check) {
            studyProgramRepository.deleteStudyProgram(name);
            System.out.println("Study program deleted successfully!");
        } else {
            System.out.println("No existing study program with this name!");
        }

        try {
            auditService.logAction("delete study program");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printStudyPrograms()
    {
        studyProgramRepository.displayStudyPrograms();

        try
        {
            auditService.logAction("print studyprograms");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addGroup()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Study program" + ": ");
        String stprog = reader.nextLine();
        stprog = stprog.substring(0,1).toUpperCase() + stprog.substring(1).toLowerCase();

        System.out.print("Group number: ");
        int number;

        while(true)
        {
            try
            {
                number = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Group number: ");
            }
        }

        groups.add(new Group(stprog, number));
        groupRepository.addGroup(stprog, number);

        try
        {
            auditService.logAction("add group");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void updateGroupNumber() {
        Scanner reader = new Scanner(System.in);

        System.out.print("Study program: ");
        String studyProgram = reader.nextLine();
        System.out.println(studyProgram);

        boolean check = false;
        for (Group g: groups) {
            System.out.println(g.getStudyProgram());
            if (g.getStudyProgram().equals(studyProgram)) {
                check = true;
                break;
            }
        }
        if (check) {
            System.out.print("New number group: ");
            int newNumber = Integer.parseInt(reader.nextLine());
            groupRepository.updateGroupNumber(studyProgram, newNumber);
            System.out.println("Group number updated successfully.");
        }else{
            System.out.println("Group with study program '" + studyProgram + "' does not exist.");
        }
        try
        {
            auditService.logAction("update group");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
}

    public void deleteGroup() {
        Scanner reader = new Scanner(System.in);

        System.out.print("Study program: ");
        String studyProgram = reader.nextLine();
        boolean check = false;

        for (Group g: groups) {
            if (g.getStudyProgram().equals(studyProgram))
                check = true;

        }
        if (check) {
            groupRepository.deleteGroup(studyProgram);
            System.out.println("Group deleted successfully!");
        } else {
            System.out.println("No existing group with this name!");
        }

        try {
            auditService.logAction("delete group");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printGroups()
    {
        groupRepository.displayGroups();

        try
        {
            auditService.logAction("print groups");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

public void addSeries()
{
    Scanner reader = new Scanner(System.in);

    boolean olympic = false;

    System.out.print("Series number: ");
    int number;
    while (true)
    {
        try
        {
            number = Integer.parseInt(reader.nextLine());
            break;
        }
        catch (NumberFormatException e)
        {
            System.out.println("Expecting an integer value. Try again!");
            System.out.print("Series number: ");
        }
    }
    if (number == 15)
        olympic = true;

    Series s = new Series(number, olympic);
    series.add(s);
    seriesRepository.addSeries(number, olympic);

    try
    {
        auditService.logAction("add series");
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }
}

    public void printSeries(){

        seriesRepository.displaySeries();

        try
        {
            auditService.logAction("print series");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addUniverity()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("University name: ");
        String name = reader.nextLine();
        name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();

        System.out.print("Address of the university: ");
        Address a = readAddress();

        universities.add(new University(name, a));
        universityRepository.addUniversity(name, a);

        try
        {
            auditService.logAction("add university");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printUniversities()
    {
        universityRepository.displayUniversities();

        try
        {
            auditService.logAction("print university");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addStudent()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Student ID: ");
        int studentId;

        while(true)
        {
            try
            {
                studentId = Integer.parseInt(reader.nextLine());

                boolean check = true;
                for (Student s : students)
                {
                    if (s.getStudentId() == studentId)
                    {
                        check = false;
                        break;
                    }
                }

                if (!check) throw new Exception();
                else break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("ID Student: ");
            }
            catch (Exception e)
            {
                System.out.println("Student with given id already exists. Try again!");
                System.out.print("ID Student: ");
            }
        }

        System.out.print("First name: ");
        String firstName = reader.nextLine();
        firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();

        System.out.print("Last name: ");
        String lastName = reader.nextLine();
        lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();

        String email;
        while(true)
        {
            System.out.print("Email: ");
            email = reader.nextLine().toLowerCase();

            if (!email.contains("@")) System.out.println("Not a valid email address! Try again!");
            else break;
        }

        System.out.print("Address: ");
        Address a = readAddress();

        System.out.print("Birth date: ");
        LocalDate d = readDate();

        StudyProgram programstudiu = new StudyProgram();
        boolean check = true;

        while(check)
        {
            System.out.print("Study Program name: ");
            String studyprogram = reader.nextLine();
            studyprogram = studyprogram.substring(0,1).toUpperCase() + studyprogram.substring(1).toLowerCase();

            for (StudyProgram dom : studyprograms)
            {
                if (dom.getName().equalsIgnoreCase(studyprogram))
                {
                    programstudiu = dom;
                    check = false;
                }
            }

            if (check)
            {
                System.out.println("Study program  doesn't exist. Try again!");
                System.out.println("Here is your option");
                for (StudyProgram dom : studyprograms) {
                    System.out.print(dom.getName());
                    System.out.print(",");
                }
                System.out.println();
            }
        }

        Group grupa = new Group();
        check = true;
        while(check)
        {
            System.out.print("Group number: ");
            int g;

            while(true)
            {
                try
                {
                    g = Integer.parseInt(reader.nextLine());
                    break;
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Expecting an integer value. Try again!");
                    System.out.print("Group number: ");
                }
            }

            for (Group gr : groups)
            {
                if (gr.getNumber() == g )
                {
                    grupa = gr;
                    check = false;
                }
            }

            if (check)
            {
                System.out.println("Group doesn't exist. Try again!");
                System.out.println("Here is your option");
                for (Group dom : groups) {
                    System.out.print(dom.getNumber());
                    System.out.print(",");
                }
                System.out.println();
            }
        }

        University universitate = new University();
        check = true;
        while(check)
        {
            System.out.print("University name: ");
            String h = reader.nextLine();

            for (University uni : universities)
            {
                if (uni.getName().equalsIgnoreCase(h))
                {
                    universitate = uni;
                    check = false;
                }
            }

            if (check)
            {
                System.out.println("University doesn't exist. Try again!");
                System.out.println("Here is your option");
                for (University dom : universities) {
                    System.out.print(dom.getName());
                    System.out.print(",");
                }
                System.out.println();
            }
        }


        Series serie = new Series();
        check = true;
        while(check)
        {
            System.out.print("Series number: ");
            int g;

            while(true)
            {
                try
                {
                    g = Integer.parseInt(reader.nextLine());
                    break;
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Expecting an integer value. Try again!");
                    System.out.print("Series number: ");
                }
            }

            for (Series gr : series)
            {
                if (gr.getNumber() == g )
                {
                    serie = gr;
                    check = false;
                }
            }

            if (check)
            {
                System.out.println("Series doesn't exist. Try again!");
                System.out.println("Here is your option");
                for (Series dom : series) {
                    System.out.print(dom.getNumber());
                    System.out.print(",");
                }
                System.out.println();
            }
        }
        students.add(new Student(studentId, firstName, lastName, email, a, d, programstudiu, grupa, universitate, serie));
        studentRepository.addStudent(studentId, firstName, lastName, email, a, d,
                programstudiu.getName(), grupa.getNumber(), universitate.getName(),serie.getNumber());

        try
        {
            auditService.logAction("add student");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printStudents()
    {
        studentRepository.displayStudents();

        try
        {
            auditService.logAction("prints students");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void printStudentById()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Student ID: ");
        int studentId;

        while(true)
        {
            try
            {
                studentId = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("ID Student: ");
            }
        }

        if (studentRepository.getStudentById(studentId) != null)
        {
            System.out.println(studentRepository.getStudentById(studentId).toString());
        }
        else
        {
            System.out.println("No existing student with this id!");
        }

        try
        {
            auditService.logAction("print student by id");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void updateStudent()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Student ID: ");
        int studentId;

        while(true)
        {
            try
            {
                studentId = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Student ID: ");
            }
        }

        boolean check = false;
        for (Student s : students)
        {
            if (studentId == s.getStudentId())
            {
                check = true;
                break;
            }
        }


        if (check)
        {

            System.out.print("New first name: ");
            String firstName = reader.nextLine();
            firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();

            System.out.print("New last name: ");
            String lastName = reader.nextLine();
            lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();

            studentRepository.updateStudentFullName(firstName, lastName, studentId);
            System.out.println("Student updated succesfully!");
        }
        else
        {
            System.out.println("No existing student with this id!");
        }

        try
        {
            auditService.logAction("update student");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteStudentById()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Student ID: ");
        int studentId;

        while(true)
        {
            try
            {
                studentId = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Student ID: ");
            }
        }

        boolean check = false;
        for (Student s : students)
        {
            if (studentId == s.getStudentId())
            {
                check = true;
                break;
            }
        }

        if (check)
        {
            studentRepository.deleteStudentById(studentId);
            System.out.println("Student deleted succesfully!");
        }
        else
        {
            System.out.println("No existing student with this id!");
        }

        try
        {
            auditService.logAction("delete student");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printSortedStudents()
    {
        List<Student> sortedStudents = students.stream().
                sorted(Comparator.comparing(Person::getLastName)).toList();

        System.out.println("Students succesfully sorted.");

        for (Student s : sortedStudents)
        {
            System.out.print(s.toString());
        }
        try
        {
            auditService.logAction("print student sorted");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Address readAddress()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("City: ");
        String city = reader.nextLine();
        city = city.substring(0,1).toUpperCase() + city.substring(1).toLowerCase();

        System.out.print("County: ");
        String county = reader.nextLine();
        county = county.substring(0,1).toUpperCase() + county.substring(1).toLowerCase();

        System.out.print("Street: ");
        String street = reader.nextLine();
        street = street.substring(0,1).toUpperCase() + street.substring(1).toLowerCase();

        System.out.print("Number: ");
        int number;

        while(true)
        {
            try
            {
                number = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Number: ");
            }
        }

        return new Address(city, county, street, number);
    }

    public LocalDate readDate()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Day: ");
        int day;

        while(true)
        {
            try
            {
                day = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Day: ");
            }
        }

        System.out.print("Month: ");
        int month;

        while(true)
        {
            try
            {
                month = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Month: ");
            }
        }

        System.out.print("Year: ");
        int year;

        while(true)
        {
            try
            {
                year = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Year: ");
            }
        }

        return LocalDate.of(year, month, day);
    }

    public void closeConnection()
    {
        DatabaseConfiguration.closeDatabaseConnection();

        try
        {
            auditService.logAction("close connection with database");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
