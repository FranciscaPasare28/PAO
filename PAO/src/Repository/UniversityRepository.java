package Repository;

import Entities.Address;
import Entities.University;
import Database.DatabaseConfiguration;
import Services.ReadWrite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UniversityRepository
{
    private static UniversityRepository universityRepository;
    private UniversityRepository() { }
    public static UniversityRepository getInstance()
    {
        if (universityRepository == null) universityRepository = new UniversityRepository();

        return universityRepository;
    }

    public void createTable()
    {
        String createTableSql = "CREATE TABLE IF NOT EXISTS UNIVERSITY " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "name varchar(60), " +
                "address varchar(100));";

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
        String selectSql = "SELECT * FROM UNIVERSITY;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            if (!resultSet.next())
            {
                List<University> universities = ReadWrite.readUniversity();

                for (University hs : universities)
                {
                    addUniversity(hs.getName(), hs.getAddress());
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addUniversity(String name, Address a)
    {
        String insertSchoolSql = "INSERT INTO UNIVERSITY(name, address) VALUES(\""
                + name + "\",\"" + a.toString() + "\");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            stmt.executeUpdate(insertSchoolSql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void displayUniversities()
    {
        String selectSql = "SELECT * FROM UNIVERSITY;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                empty = false;
                System.out.println("University Name: " + resultSet.getString(2));
                System.out.println("Address: " + resultSet.getString(3));
                System.out.println();
            }

            if (empty)
            {
                System.out.println("No existing university!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
