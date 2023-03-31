package Services;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class Menu
{
    // singleton class for an interactive menu

    private static Menu menu = null;
    private static final Service serviciu = new Service();

    // private constructor
    private Menu() { }

    public static void showMenu()
    {
        System.out.println("-----------------------------------");
        System.out.println("Choose an action.");
        System.out.println("1: Add new subject");
        System.out.println("2: Print all subjects");
        System.out.println("3: Add new study program");
        System.out.println("4: Print all study programs");
        System.out.println("5: Add new group");
        System.out.println("6: Print all groups");
        System.out.println("7: Add new series");
        System.out.println("8: Print all series");
        System.out.println("9: Add new university");
        System.out.println("10: Print all universities");
        System.out.println("11: Add new student");
        System.out.println("12: Print all students");
        System.out.println("13: Sort students by average grade");
        System.out.println("14: Average grade of a student");
        System.out.println("15: Delete a student");

        System.out.println("Exit");
        System.out.println("-----------------------------------");
        System.out.println("Your choice:");
        Scanner reader = new Scanner(System.in);
        String option = reader.nextLine();

        while (!option.equalsIgnoreCase("exit"))
        {
            switch(option)
            {
                case "exit":
                {
                    System.out.println("Exiting program.");
                    break;
                }
                case "1":
                {
                    serviciu.addSubject();
                    System.out.println("-----------------------------------");
                    System.out.println("Now you can choose another option from the menu mentioned above or you can write \"help\" to be able to see the menu again");
                    System.out.println("Your choice: ");
                    break;
                }
                case "2":
                {
                    serviciu.printSubjects();
                    System.out.println("-----------------------------------");
                    System.out.println("Now you can choose another option from the menu mentioned above or you can write \"help\" to be able to see the menu again");
                    System.out.println("Your choice: ");

                    break;
                }
                case "3":
                {
                    serviciu.addStudyProgram();
                    System.out.println("-----------------------------------");
                    System.out.println("Now you can choose another option from the menu mentioned above or you can write \"help\" to be able to see the menu again");
                    System.out.println("Your choice: ");

                    break;
                }
                case "4":
                {
                    serviciu.printStudyPrograms();
                    System.out.println("-----------------------------------");
                    System.out.println("Now you can choose another option from the menu mentioned above or you can write \"help\" to be able to see the menu again");
                    System.out.println("Your choice: ");

                    break;
                }
                case "5":
                {
                    serviciu.addGroup();
                    System.out.println("-----------------------------------");
                    System.out.println("Now you can choose another option from the menu mentioned above or you can write the number \"help\" to be able to see the menu again");
                    System.out.println("Your choice: ");

                    break;
                }
                case "6":
                {
                    serviciu.printGroups();
                    System.out.println("-----------------------------------");
                    System.out.println("Now you can choose another option from the menu mentioned above or you can write the number \"help\" to be able to see the menu again");
                    System.out.println("Your choice: ");

                    break;
                }case "7":
                {
                    serviciu.addSeries();
                    System.out.println("-----------------------------------");
                    System.out.println("Now you can choose another option from the menu mentioned above or you can write the number \"help\" to be able to see the menu again");
                    System.out.println("Your choice: ");

                    break;
                }case "8":
                {
                    serviciu.printSeries();
                    System.out.println("-----------------------------------");
                    System.out.println("Now you can choose another option from the menu mentioned above or you can write the number \"help\" to be able to see the menu again");
                    System.out.println("Your choice: ");

                    break;
                }
                case "9":
                {
                    serviciu.addUniverity();
                    System.out.println("-----------------------------------");
                    System.out.println("Now you can choose another option from the menu mentioned above or you can write the number \"help\" to be able to see the menu again");
                    System.out.println("Your choice: ");

                    break;
                }
                case "10":
                {
                    serviciu.printUniversities();
                    System.out.println("-----------------------------------");
                    System.out.println("Now you can choose another option from the menu mentioned above or you can write the number \"help\" to be able to see the menu again");
                    System.out.println("Your choice: ");

                    break;
                }
                case "11":
                {
                    serviciu.addStudent();
                    System.out.println("-----------------------------------");
                    System.out.println("Now you can choose another option from the menu mentioned above or you can write the number \"help\" to be able to see the menu again");
                    System.out.println("Your choice: ");

                    break;
                }
                case "12":
                {
                    serviciu.printStudents();
                    System.out.println("-----------------------------------");
                    System.out.println("Now you can choose another option from the menu mentioned above or you can write the number \"help\" to be able to see the menu again");
                    System.out.println("Your choice: ");

                    break;
                }
                case "13":
                {
                    serviciu.sortStudent();
                    System.out.println("-----------------------------------");
                    System.out.println("Now you can choose another option from the menu mentioned above or you can write the number \"help\" to be able to see the menu again");
                    System.out.println("Your choice: ");

                    break;
                }
                case "14":
                {
                    serviciu.meanMarkStudent();
                    System.out.println("-----------------------------------");
                    System.out.println("Now you can choose another option from the menu mentioned above or you can write the number \"help\" to be able to see the menu again");
                    System.out.println("Your choice: ");

                    break;
                }
                case "help":
                {
                    showMenu();
                    break;
                }
                case "15":
                {
                    serviciu.deleteStudent();
                    System.out.println("-----------------------------------");
                    System.out.println("Now you can choose another option from the menu mentioned above or you can write the number \"help\" to be able to see the menu again");
                    System.out.println("Your choice: ");
                    break;
                }
                default:
                {
                    System.out.println("Invalid option. Try again.");
                    break;
                }
            }

            option = reader.nextLine();
        }
        reader.close();
    }

    public static Menu getInstance()
    {
        if (menu == null) menu = new Menu();

        return menu;
    }
}
