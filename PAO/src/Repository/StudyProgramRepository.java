package Repository;

import Entities.StudyProgram;
import Database.DatabaseConfiguration;
import Services.ReadWrite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
public class StudyProgramRepository {
    private static StudyProgramRepository studyprogramRepository;
    private StudyProgramRepository() { }
    public static StudyProgramRepository getInstance()
    {
        if (studyprogramRepository == null) studyprogramRepository = new StudyProgramRepository();

        return studyprogramRepository;
    }

    public void createTable()
    {
        String createTableSql = "CREATE TABLE IF NOT EXISTS StudyProgram " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "name varchar(40), " +
                "levelOfStudy varchar(40));";

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
        String selectSql = "SELECT * FROM StudyProgram;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            if (!resultSet.next())
            {
                List<StudyProgram> studyPrograms = ReadWrite.readStudyProgram();

                for (StudyProgram d : studyPrograms)
                {
                    addStudyProgram(d.getName(), d.getLevelOfStudy());
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addStudyProgram(String name, String levelOfStudy)
    {
        String insertStudyProgramSql = "INSERT INTO StudyProgram(name, levelOfStudy) VALUES(\""
                + name + "\", '" + levelOfStudy + "');";


        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            stmt.executeUpdate(insertStudyProgramSql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateStudyProgram (String name, String newlevelOfStudy) {
        String updateSql = "UPDATE STUDYPROGRAM SET levelOfStudy = '" + newlevelOfStudy +
                "' WHERE name = \"" + name + "\";";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(updateSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteStudyProgram(String name) {
        String deleteSql = "DELETE FROM STUDYPROGRAM WHERE name = \"" + name + "\";";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(deleteSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayStudyPrograms()
    {
        String selectSql = "SELECT * FROM StudyProgram;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                empty = false;
                System.out.println("Study Program name: " + resultSet.getString(2));
                System.out.println("Level of Study: " + resultSet.getString(3));
                System.out.println();
            }

            if (empty)
            {
                System.out.println("No existing studyPrograms!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
