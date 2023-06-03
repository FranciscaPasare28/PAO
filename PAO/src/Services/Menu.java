package Services;

import java.util.Scanner;

public class Menu
{
    private static Menu menu = null;
    private static final Service serviciu = Service.getInstance();

    private Menu()
    {
        serviciu.configureTables();
        serviciu.loadData();
    }

    private void showMenu()
    {
        System.out.println("-----------------------------------");
        System.out.println("Choose an action.");
        System.out.println("1: Add new subject");
        System.out.println("2: Print all subjects");
        System.out.println("3: Update a grade according to the student's id");
        System.out.println("4: Delete a grade after the student ID");

        System.out.println("5: Add new study program");
        System.out.println("6: Print all study programs");
        System.out.println("7: Update the study program level by name");
        System.out.println("8: Delete a study program by name");

        System.out.println("9: Add new group");
        System.out.println("10: Print all groups");
        System.out.println("11: Update the group number by name");
        System.out.println("12: Delete a group by name");

        System.out.println("13: Add new series");
        System.out.println("14: Print all series");

        System.out.println("15: Add new university");
        System.out.println("16: Print all universities");

        System.out.println("17: Add new student");
        System.out.println("18: Print all students");
        System.out.println("19: Print students sorted alphabetically");
        System.out.println("20: Print student by a given id");
        System.out.println("21: Update student name by a given id");
        System.out.println("22: Delete student by a given id");

        System.out.println("0: Exit");
        System.out.println("-----------------------------------");
    }

    public void runMenu()
    {
        showMenu();

        Scanner reader = new Scanner(System.in);
        int option;
        String check;

        do
        {
            option = reader.nextInt();
            reader.nextLine();

            switch (option) {
                case 0 -> {
                    serviciu.closeConnection();
                    System.out.println("Exiting program..");
                }
                case 1 -> {
                    serviciu.addSubject();
                    System.out.println("-----------------------------------");
                }
                case 2 -> {
                    serviciu.printSubjects();
                    System.out.println("-----------------------------------");
                }
                case 3 -> {
                    serviciu.updateSubject();
                    System.out.println("-----------------------------------");
                }
                case 4 -> {
                    serviciu.deleteSubject();
                    System.out.println("-----------------------------------");
                }
                case 5 -> {
                    serviciu.addStudyProgram();
                    System.out.println("-----------------------------------");
                }
                case 6 -> {
                    serviciu.printStudyPrograms();
                    System.out.println("-----------------------------------");
                }
                case 7 -> {
                    serviciu.updateStudyProgram();
                    System.out.println("-----------------------------------");
                }
                case 8 -> {
                    serviciu.deleteStudyProgram();
                    System.out.println("-----------------------------------");
                }
                case 9 -> {
                    serviciu.addGroup();
                    System.out.println("-----------------------------------");
                }
                case 10 -> {
                    serviciu.printGroups();
                    System.out.println("-----------------------------------");
                }
                case 11 -> {
                    serviciu.updateGroupNumber();
                    System.out.println("-----------------------------------");
                }
                case 12 -> {
                    serviciu.deleteGroup();
                    System.out.println("-----------------------------------");
                }
                case 13 -> {
                    serviciu.addSeries();
                    System.out.println("-----------------------------------");
                }
                case 14 -> {
                    serviciu.printSeries();
                    System.out.println("-----------------------------------");
                }
                case 15 -> {
                    serviciu.addUniverity();
                    System.out.println("-----------------------------------");
                }
                case 16 -> {
                    serviciu.printUniversities();
                    System.out.println("-----------------------------------");
                }
                case 17 -> {
                    serviciu.addStudent();
                    System.out.println("-----------------------------------");
                }
                case 18 -> {
                    serviciu.printStudents();
                    System.out.println("-----------------------------------");
                }
                case 19 -> {
                    serviciu.printSortedStudents();
                    System.out.println("-----------------------------------");
                }
                case 20 -> {
                    serviciu.printStudentById();
                    System.out.println("-----------------------------------");
                }
                case 21 -> {
                    serviciu.updateStudent();
                    System.out.println("-----------------------------------");
                }
                case 22 -> {
                    serviciu.deleteStudentById();
                    System.out.println("-----------------------------------");
                }
                default -> System.out.println("Invalid option. Try again.");
            }

            if (option != 0)
            {
                System.out.println("Do you want another action? y/n");
                check = reader.nextLine();
                check = check.toLowerCase();

                if (check.equals("y")) showMenu();
                else {
                    option = 0;
                    serviciu.closeConnection();
                    System.out.println("Exiting program..");
                }
            }

        } while (option != 0);

        reader.close();
    }

    public static Menu getInstance()
    {
        if (menu == null) menu = new Menu();

        return menu;
    }
}
