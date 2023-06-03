//package Services1;
//
//import Entities.Group;
//import Repository.GroupRepository;
//import Services.AuditService;
//import java.io.IOException;
//import java.util.Scanner;
//
//public class GroupService {
//    private GroupRepository groupRepository = GroupRepository.getInstance();
//    public GroupService(){
//        this.groupRepository = new GroupRepository();
//    }
//    public void addGroup()
//    {
//        Scanner reader = new Scanner(System.in);
//
//        System.out.print("Domain" + ": ");
//        String domain = reader.nextLine();
//        domain = domain.substring(0,1).toUpperCase() + domain.substring(1).toLowerCase();
//
//        System.out.print("Group number: ");
//        int number;
//
//        while(true)
//        {
//            try
//            {
//                number = Integer.parseInt(reader.nextLine());
//                break;
//            }
//            catch (NumberFormatException e)
//            {
//                System.out.println("Expecting an integer value. Try again!");
//                System.out.print("Group number: ");
//            }
//        }
//
//        groups.add(new Group(domain, number));
//        //ReadWrite.writeGroup(domain, number);
//        groupRepository.addGroup(domain, number);
//
//        try
//        {
//            auditService.logAction("add group");
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//    //    public void updateGroupNumber(){
////        Scanner reader = new Scanner(System.in);
////
////        System.out.print("Study program: ");
////        String studyprogram;
////        studyprogram = reader.nextLine();
////
////        boolean check = false;
////        for (Group g : groups)
////        {
////            if (studyprogram == g.getStudyProgram())
////            {
////                check = true;
////                break;
////            }
////        }
////
////        if (check)
////        {
////
////            System.out.print("New number group: ");
////            int number = Integer.parseInt(reader.nextLine());
////
////            groupRepository.updateGroupNumber(studyprogram, number);
////            System.out.println("Student updated succesfully!");
////        }
////        else
////        {
////            System.out.println("No existing student with this id!");
////        }
////
////        try
////        {
////            auditService.logAction("update student");
////        }
////        catch (IOException e)
////        {
////            e.printStackTrace();
////        }
////    }
//    public void updateGroupNumber() {
//        Scanner reader = new Scanner(System.in);
//
//        System.out.print("Study program: ");
//        String studyProgram = reader.nextLine();
//
//        // Verificăm dacă există un grup cu programul de studiu specificat
//        boolean existingGroup = false;
//        for (Group g: groups) {
//            if (g.getStudyProgram().equals(studyProgram))
//                existingGroup = true;
//        }
//        if (existingGroup == true) {
//            System.out.println("Group with study program '" + studyProgram + "' does not exist.");
//            return;
//        }
//
//        System.out.print("New number group: ");
//        int newNumber = Integer.parseInt(reader.nextLine());
//
//        // Actualizăm numărul grupului
//        groupRepository.updateGroupNumber(studyProgram, newNumber);
//        System.out.println("Group number updated successfully.");
//    }
//
//
//    public void printGroups()
//    {
//        groupRepository.displayGroups();
//
//        try
//        {
//            auditService.logAction("print groups");
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    }
////    public void addSeries()
////    {
////        Scanner reader = new Scanner(System.in);
////
////        boolean olimpic=false;
////
////        System.out.print("Series number: ");
////        int number;
////        while(true)
////        {
////            try
////            {
////                number = Integer.parseInt(reader.nextLine());
////                break;
////            }
////            catch (NumberFormatException e)
////            {
////                System.out.println("Expecting an integer value. Try again!");
////                System.out.print("Series number: ");
////            }
////        }
////        if(number==15)
////            olimpic=true;
////
////        Series s = new Series(number,olimpic);
////        series.add(s);
////    }
////
////    public void printSeries()
////    {
////        if (series.isEmpty())
////        {
////            System.out.println("No existing series!");
////        }
////        else
////        {
////            for (Series s :series)
////            {
////                System.out.printf("Series number: %d\n", s.getNumber());
////                System.out.printf("Olympic: %b\n", s.isOlympic());
////                System.out.println();
////            }
////        }
////    }
//}
