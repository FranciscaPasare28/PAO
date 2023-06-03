package Repository;

import Entities.Series;
import Database.DatabaseConfiguration;
import Entities.StudyProgram;
import Services.ReadWrite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SeriesRepository
{
    private static SeriesRepository seriesRepository;
    private SeriesRepository() { }
    public static SeriesRepository getInstance()
    {
        if (seriesRepository == null) seriesRepository = new SeriesRepository();

        return seriesRepository;
    }

    public void createTable()
    {
        String createTableSql = "CREATE TABLE IF NOT EXISTS STUDENTSERIES " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "number int, " +
                "olympic boolean);";

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
        String selectSql = "SELECT * FROM STUDENTSERIES;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            if (!resultSet.next())
            {
                List<Series> series = ReadWrite.readSeries();

                for (Series g : series)
                {
                    addSeries(g.getNumber(), g.isOlympic());
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addSeries(int number, boolean olympic)
    {
        String insertSeriesSql = "INSERT INTO STUDENTSERIES(number, olympic) VALUES(\""
                + number + "\", " + olympic + ");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            stmt.executeUpdate(insertSeriesSql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void displaySeries()
    {
        String selectSql = "SELECT * FROM STUDENTSERIES;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                empty = false;
                System.out.println("Series number: " + resultSet.getInt(2));
                System.out.println("Omylpic: " + resultSet.getBoolean(3));
                System.out.println();
            }

            if (empty)
            {
                System.out.println("No existing series!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
