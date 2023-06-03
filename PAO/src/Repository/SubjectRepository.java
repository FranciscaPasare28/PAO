package Repository;

import Entities.StudyProgram;
import Entities.Subject;
import Database.DatabaseConfiguration;
import Services.ReadWrite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SubjectRepository
{
    private static SubjectRepository subjectRepository;
    private SubjectRepository() { }
    public static SubjectRepository getInstance()
    {
        if (subjectRepository == null) subjectRepository = new SubjectRepository();

        return subjectRepository;
    }

    public void createTable()
    {
        String createTableSql = "CREATE TABLE IF NOT EXISTS SUBJECT " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "student int," +
                "name varchar(40), " +
                "mark int);";

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
        String selectSql = "SELECT * FROM SUBJECT;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            if (!resultSet.next())
            {
                List<Subject> subjects = ReadWrite.readSubject();

                for (Subject s : subjects)
                {
                    addSubject(s.getStudentId(), s.getSubjectName(), s.getMark());
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addSubject(int studentId, String name, int mark)
    {
        String insertSubjectSql = "INSERT INTO SUBJECT(student, name, mark) VALUES("
                + studentId + ", \"" + name + "\", " + mark + ");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            stmt.executeUpdate(insertSubjectSql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateSubject(int studentId, String subjectName, int mark) {
        String updateSubjectSql = "UPDATE SUBJECT SET mark = " + mark + " WHERE student = " + studentId + " AND name = '" + subjectName + "'";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(updateSubjectSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteSubject(int studentId) {
        String deleteSubjectSql = "DELETE FROM SUBJECT WHERE student = " + studentId;

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(deleteSubjectSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void displaySubjects()
    {
        String selectSql = "SELECT * FROM SUBJECT;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                empty = false;
                System.out.println("Student ID: " + resultSet.getInt(2));
                System.out.println("Subject Name: " + resultSet.getString(3));
                System.out.println("Mark: " + resultSet.getInt(4));
                System.out.println();
            }

            if (empty)
            {
                System.out.println("No existing subjects!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
