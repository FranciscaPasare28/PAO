package Repository;

import Entities.*;
import Database.DatabaseConfiguration;
import Services.ReadWrite;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static Services.Service.*;

public class StudentRepository
{
    private static StudentRepository studentRepository;
    private StudentRepository() { }
    public static StudentRepository getInstance()
    {
        if (studentRepository == null) studentRepository = new StudentRepository();

        return studentRepository;
    }

    public void createTable()
    {
        String createTableSql = "CREATE TABLE IF NOT EXISTS STUDENT " +
                "(id int PRIMARY KEY, " +
                "firstname varchar(40), " +
                "lastname varchar(30), " +
                "email varchar(40), " +
                "address varchar(100), " +
                "birthdate date, " +
                "studyProgram varchar(40), " +
                "groupnumber int, " +
                "university varchar(60), " +
                "serienumber int);";

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
        String selectSql = "SELECT * FROM STUDENT;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            if (!resultSet.next())
            {
                List<Student> students = ReadWrite.readStudent();

                for (Student s : students)
                {
                    addStudent(s.getStudentId(), s.getFirstName(), s.getLastName(), s.getEmail(),
                            s.getAddress(), s.getBirthDate(), s.getStudyProgram().getName(),
                            s.getGroup().getNumber(), s.getUniversity().getName(),s.getSeries().getNumber());
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void addStudent(int id, String firstname, String lastname, String email, Address a,
                       LocalDate date, String studyProgram, int groupnumber, String university, int serienumber) {

        String insertStudentSql = "INSERT INTO STUDENT (id, firstname, lastname, email, address, birthdate, studyProgram, groupnumber, university, serienumber) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertStudentSql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, firstname);
            preparedStatement.setString(3, lastname);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, a.toString());
            preparedStatement.setDate(6, java.sql.Date.valueOf(date));
            preparedStatement.setString(7, studyProgram);
            preparedStatement.setInt(8, groupnumber);
            preparedStatement.setString(9, university);
            preparedStatement.setInt(10, serienumber);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
}

    public void displayStudents()
    {

        String selectSql = "SELECT * FROM STUDENT;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                empty = false;
                System.out.println("Student ID: " + resultSet.getInt(1));
                System.out.println("First name: " + resultSet.getString(2));
                System.out.println("Last name: " + resultSet.getString(3));
                System.out.println("Email: " + resultSet.getString(4));
                System.out.println("Address: " + resultSet.getString(5));
                System.out.println("Birth Date: " + resultSet.getDate(6).toString());
                System.out.println("StudyProgram name: " + resultSet.getString(7));
                System.out.println("Group number: " + resultSet.getInt(8));
                System.out.println("University: " + resultSet.getString(9));
                System.out.println("Serie number: " + resultSet.getInt(10));
                System.out.println();

            }

            if (empty)
            {
                System.out.println("No existing students!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Student getStudentById(int id)
    {
        String selectSql = "SELECT * FROM STUDENT WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql))
        {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToStudent(resultSet);
        }
        catch (SQLException e)
        {
            return null;
        }

    }

    public void updateStudentFullName(String firstname, String lastname, int id)
    {
        String updateNameSql = "UPDATE STUDENT SET firstname=?, lastname=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql))
        {
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteStudentById(int id)
    {
        String deleteStudentSql = "DELETE FROM STUDENT WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteStudentSql))
        {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private Student mapToStudent(ResultSet resultSet) throws SQLException
    {
        if (resultSet.next())
        {
            String[] a = resultSet.getString(5).split(",");
            Address address = new Address(a[0], a[1], a[2], Integer.parseInt(a[3]));

            LocalDate date = LocalDate.parse(resultSet.getString(6));

            StudyProgram stpr = new StudyProgram();
            String progstudiu = resultSet.getString(7);

            for (StudyProgram ps: studyprograms)
            {
                if (progstudiu.equals(ps.getName()))
                {
                    stpr = ps;
                    break;
                }
            }

            Group gr = new Group();
            int grupa = resultSet.getInt(8);

            for (Group g: groups)
            {
                if (grupa == g.getNumber())
                {
                    System.out.println(grupa);
                    System.out.println(g.getNumber());
                    gr = g;
                    break;
                }
            }

            Series ser = new Series();
            int serie = resultSet.getInt(8);

            for (Series g: series)
            {
                if (serie == g.getNumber())
                {
                    ser = g;
                    break;
                }
            }

            University university = new University();
            String hs = resultSet.getString(9);

            for (University h : universities)
            {
                if (hs.equals(h.getName()))
                {
                    university = h;
                    break;
                }
            }

            return new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), address, date, stpr, gr, university,ser);
        }
        return null;
    }
}
