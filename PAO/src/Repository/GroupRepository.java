package Repository;
import Database.DatabaseConfiguration;
import Entities.Group;
import Services.ReadWrite;

import java.sql.*;
import java.util.List;

public class GroupRepository
{
    private static GroupRepository groupRepository;
    private GroupRepository() { }
    public static GroupRepository getInstance()
    {
        if (groupRepository == null) groupRepository = new GroupRepository();

        return groupRepository;
    }

    public void createTable()
    {
        String createTableSql = "CREATE TABLE IF NOT EXISTS STUDENTGROUP " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "studyprogram varchar(40), " +
                "number int);";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            stmt.execute(createTableSql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public void addData()
    {
        String selectSql = "SELECT * FROM STUDENTGROUP;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            if (!resultSet.next())
            {
                List<Group> groups = ReadWrite.readGroup();

                for (Group g : groups)
                {
                    addGroup(g.getStudyProgram(), g.getNumber());
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public void addGroup(String studyprogram, int number)
    {
        String insertGroupSql = "INSERT INTO STUDENTGROUP(studyprogram, number) VALUES(\""
                + studyprogram + "\", " + number + ");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            stmt.executeUpdate(insertGroupSql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateGroupNumber (String studyProgram, int newNumber) {
    String updateSql = "UPDATE STUDENTGROUP SET number = " + newNumber +
            " WHERE studyprogram = \"" + studyProgram + "\";";

    Connection connection = DatabaseConfiguration.getDatabaseConnection();

    try (Statement stmt = connection.createStatement()) {
        stmt.executeUpdate(updateSql);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    public void deleteGroup(String studyProgram) {
        String deleteSql = "DELETE FROM STUDENTGROUP WHERE studyprogram = \"" + studyProgram + "\";";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(deleteSql);
            System.out.println("Group with study program '" + studyProgram + "' deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayGroups()
    {
        String selectSql = "SELECT * FROM STUDENTGROUP;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                empty = false;
                System.out.println("Study program name: " + resultSet.getString(2));
                System.out.println("Group number: " + resultSet.getInt(3));
                System.out.println();
            }

            if (empty)
            {
                System.out.println("No existing groups!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
