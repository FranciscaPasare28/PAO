package Services;

import java.time.LocalDate;
import java.util.*;

import Entities.*;

public class Service
{
    private final List<Subject> subjects = new ArrayList<>();
    private final Set<StudyProgram> studyPrograms = new HashSet();
    private final List<Group> groups = new ArrayList<>();
    private final List<University> universities = new ArrayList<>();
    private final List<Student> students = new ArrayList<>();
    private final List<Series> series = new ArrayList<>();

    public void addSubject()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Name of the subject: ");
        String subjectName = reader.nextLine();

        int grade=0;

        Subject sub = new Subject(subjectName, grade);
        subjects.add(sub);
    }

    public void printSubjects()
    {
        if (subjects.isEmpty())
        {
            System.out.println("No existing subjects!");
        }
        else
        {
            for (Subject s : subjects)
            {
                System.out.printf("Subject name: %s\n", s.getName());
                System.out.println();
            }
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

            if (studyprogram.equalsIgnoreCase("CS"))
            {
                studyprogram = "Computer Science";
            }
            else if (studyprogram.equalsIgnoreCase("M"))
            {
                studyprogram = "Mathematics";
            }
            else if (studyprogram.equalsIgnoreCase("CIT"))
            {
                studyprogram = "Computers and information technology";
            }
            else {
                throw new IllegalArgumentException("Invalid study program entered");
            }

           // System.out.println("Selected study program: " + studyprogram);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.print("Level of study: ");
            System.out.println("You can choose one of: BD(Bachelor's degree)/MD(Master's degree)/DD(Doctoral degree)");
            levelofStudy = reader.nextLine();

            if (levelofStudy.equalsIgnoreCase("BD"))
            {
                levelofStudy = "Bachelor's degree";
            }
            else if (levelofStudy.equalsIgnoreCase("MD"))
            {
                levelofStudy = "Master's degree";
            }
            else if (levelofStudy.equalsIgnoreCase("DD"))
            {
                levelofStudy = "Doctoral degree";
            }
            else {
                throw new IllegalArgumentException("Invalid level of study entered");
            }

            //System.out.println("Selected level of study program: " + levelofStudy);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }


        StudyProgram stdprg = new StudyProgram(studyprogram, levelofStudy);
        studyPrograms.add(stdprg);
    }

    public void printStudyPrograms()
    {
        if (studyPrograms.isEmpty())
        {
            System.out.println("No existing study programs!");
        }
        else
        {
            for (StudyProgram sp :studyPrograms)
            {
                System.out.printf("The name of the study program: %s\n", sp.getName());
                System.out.printf("Level of study: %s\n", sp.getLevelOfStudy());
                System.out.println();
            }
        }
    }

    public void addGroup()
    {
        Scanner reader = new Scanner(System.in);

        StudyProgram programstudiu = new StudyProgram();
        boolean check = true;

        while(check)
        {
            System.out.print("The name of the study program: ");
            String domain = reader.nextLine();

            for (StudyProgram s : studyPrograms)
            {
                if (s.getName().equalsIgnoreCase(domain))
                {
                    programstudiu = s;
                    check = false;
                }
            }

            if (check) {
                if(studyPrograms.isEmpty()) {
                    System.out.println("You need to add a program study. Press \"3\" for that");
                    return;
                }
                else{
                    System.out.println("You must choose an option below or add a new one: ");
                    for (StudyProgram s : studyPrograms)
                        System.out.println(s.getName());
                }
            }
        }

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

        Group g = new Group(number,programstudiu);
        groups.add(g);
    }

    public void printGroups()
    {
        if (groups.isEmpty())
        {
            System.out.println("No existing groups!");
        }
        else
        {
            for (Group g :groups)
            {
                System.out.printf("Group number: %d\n", g.getNumber());
                System.out.printf("Program study: %s\n", g.getStudyProgram().getName());
                System.out.println();
            }
        }
    }

    public void addSeries()
    {
        Scanner reader = new Scanner(System.in);

        boolean olimpic=false;

        System.out.print("Series number: ");
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
                System.out.print("Series number: ");
            }
        }
        if(number==15)
            olimpic=true;

        Series s = new Series(number,olimpic);
        series.add(s);
    }

    public void printSeries()
    {
        if (series.isEmpty())
        {
            System.out.println("No existing series!");
        }
        else
        {
            for (Series s :series)
            {
                System.out.printf("Series number: %d\n", s.getNumber());
                System.out.printf("Olympic: %b\n", s.isOlympic());
                System.out.println();
            }
        }
    }
    public void addUniverity()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("University name: ");
        String name = reader.nextLine();

        System.out.print("Address of the university:\n");
        Address a = readAddress();

        University hs = new University(name, a);
        universities.add(hs);
    }

    public void printUniversities()
    {
        if (universities.isEmpty())
        {
            System.out.println("No existing universities!");
        }
        else
        {
            for (University c : universities)
            {
                System.out.printf("University name: %s\n", c.getName());
                System.out.printf("Address: %s\n", c.getAddress().toString());
                System.out.println();
            }
        }
    }

    public void addStudent()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("First name: ");
        String firstName = reader.nextLine();

        System.out.print("Last name: ");
        String lastName = reader.nextLine();

        String email;
        System.out.print("Email: ");
        email = reader.nextLine();
        if (!email.contains("@")){
            do {
                System.out.print("A valid email contains \"@\": ");
                email = reader.nextLine();
            } while(!email.contains("@"));
        }

        System.out.println("Address: ");
        Address a = readAddress();

        System.out.println("Birth date: ");
        LocalDate d = readDate();

        System.out.print("ID Student: ");
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

        System.out.print("Number of subjects: ");
        int n;

        while(true)
        {
            try
            {
                n = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Number of subjects: ");
            }
        }

        List<Subject> subjects1 = new ArrayList<>();
        boolean check = false;
        for (int i = 0; i < n; i ++)
        {
            String name=null;
            while(!check) {
                System.out.print("Subject name: ");
                name = reader.nextLine();
                for (Subject s : subjects)
                    if (s.getName().equalsIgnoreCase(name))
                        check = true;
                if (!check) {
                    if (subjects.isEmpty()) {
                        System.out.println("You need to add a subject. Press \"1\" for that");
                        return;
                    } else {
                        System.out.println("You must choose an option below or add a new one: ");
                        for (Subject s : subjects)
                            System.out.println(s.getName());
                    }
                }
            }
            check=false;
            System.out.print("Subject grade: ");
            int grade;

            while(true)
            {
                try
                {
                    grade = Integer.parseInt(reader.nextLine());
                    break;
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Expecting an integer value. Try again!");
                    System.out.print("Subject grade: ");
                }
            }

            Subject s = new Subject(name, grade);
            subjects1.add(s);
        }

        StudyProgram programstudiu = new StudyProgram();
        check = true;

        while(check)
        {
            System.out.print("Domain name: ");
            String domain = reader.nextLine();

            for (StudyProgram s : studyPrograms)
            {
                if (s.getName().equalsIgnoreCase(domain))
                {
                    programstudiu = s;
                    check = false;
                }
            }

            if (check)
            {
                if(studyPrograms.isEmpty()) {
                    System.out.println("You need to add a study program. Press \"3\" for that");
                    return;
                }
                else{
                    System.out.println("You must choose an option below or add a new one: ");
                    for (StudyProgram s : studyPrograms)
                        System.out.println(s.getName());
                }
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
                if (gr.getNumber() == g)
                {
                    grupa = gr;
                    check = false;
                }
            }

            if (check)
            {
                if(groups.isEmpty()) {
                    System.out.println("You need to add a group. Press \"5\" for that");
                    return;
                }
                else{
                    System.out.println("You must choose an option below or add a new one: ");
                    for (Group gr : groups)
                        System.out.println(gr.getNumber());
                }
            }
        }

        University facultate = new University();
        check = true;
        while(check)
        {
            System.out.print("University name: ");
            String h = reader.nextLine();

            for (University c : universities)
            {
                if (c.getName().equalsIgnoreCase(h))
                {
                    facultate = c;
                    check = false;
                }
            }

            if (check)
            {
                if(universities.isEmpty()) {
                    System.out.println("You need to add a university. Press \"9\" for that");
                    return;
                }
                else{
                    System.out.println("You must choose an option below or add a new one: ");
                    for (University c : universities)
                        System.out.println(c.getName());
                }
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
                if (gr.getNumber() == g)
                {
                    serie = gr;
                    check = false;
                }
            }

            if (check)
            {
                if(series.isEmpty()) {
                    System.out.println("You need to add a series. Press \"7\" for that");
                    return;
                }
                else{
                    System.out.println("You must choose an option below or add a new one: ");
                    for (Series gr : series)
                        System.out.println(gr.getNumber());
                }
            }
            if(serie.getNumber()==15)
                serie.setOlympic(true);
        }

        Student s = new Student(firstName, lastName, email, a, d , studentId, subjects1, programstudiu, grupa, facultate,serie);
        students.add(s);
    }

    public void printStudents()
    {
        if (students.isEmpty())
        {
            System.out.println("No existing students!");
        }
        else
        {
            for (Student s: students)
            {
                String s1 = s.toString();
                System.out.println(s1);
            }
        }
    }

    public void sortStudent()
    {
        Collections.sort(students, Collections.reverseOrder());
        System.out.println("The list of students sorted in descending order according to the general average.");
        printStudents();

    }

    public void meanMarkStudent()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Student ID: ");
        int id;

        while(true)
        {
            try
            {
                id = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Group number: ");
            }
        }

        boolean check = false;
        for (Student s: students)
        {
            if (s.getStudentId() == id)
            {
                System.out.printf("Mean grade: %f\n", s.meanAverage());
                check = true;
                break;
            }
        }
        if (!check)
        {
            System.out.println("No student found with this ID");
        }
    }

    public void deleteStudent()
    {
        Scanner reader = new Scanner(System.in);
        if (students.isEmpty())
        {
            System.out.println("No existing students!");
            System.out.println("You need to add a student. Press \"11\" for that");
            return;
        }
        System.out.print("Student ID: ");
        int id;

        while(true)
        {
            try
            {
                id = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Group number: ");
            }
        }

        boolean check = false;
        for (Student s: students)
        {
            if (s.getStudentId() == id)
            {
                System.out.printf("Student with id %d has been deleted", id);
                for(Student st:students)
                    if(st.getStudentId()==id) {
                        students.remove(st);
                        break;
                    }
                check = true;
                return;
            }
        }
        if (!check)
        {
            System.out.println("No student found with this ID");
            return;
        }
    }

    public Address readAddress()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("->City: ");
        String city = reader.nextLine();

        System.out.print("->County: ");
        String county = reader.nextLine();

        System.out.print("->Street: ");
        String street = reader.nextLine();

        System.out.print("->State: ");
        String state = reader.nextLine();

        System.out.print("->Zip code: ");
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
                System.out.print("Zip code: ");
            }
        }

        return new Address(street, city, state, county, number);
    }

    public LocalDate readDate()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("->Day: ");
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

        System.out.print("->Month: ");
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

        System.out.print("->Year: ");
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
}
