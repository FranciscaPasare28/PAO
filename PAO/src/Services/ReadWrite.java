package Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static Services.Service.*;

import Database.DatabaseConfiguration;
import Entities.*;

public class ReadWrite {
    public static List<StudyProgram> readStudyProgram()
    {
        List<StudyProgram> studyPrograms = new ArrayList<>();
        String selectSql = "SELECT * FROM STUDYPROGRAM;";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                String levelOfStudy = resultSet.getString("LEVELOFSTUDY");

                StudyProgram studyProgram = new StudyProgram(name, levelOfStudy);
                studyPrograms.add(studyProgram);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studyPrograms;
    }

    public static List<Group> readGroup() {
        List<Group> groups = new ArrayList<>();
        String selectSql = "SELECT * FROM STUDENTGROUP;";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            while (resultSet.next()) {
                String studyprogram = resultSet.getString("STUDYPROGRAM");
                int number = resultSet.getInt("NUMBER");

                Group group = new Group(studyprogram, number);
                groups.add(group);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return groups;
    }

    public static List<Series> readSeries() {
        List<Series> series = new ArrayList<>();
        String selectSql = "SELECT * FROM STUDENTSERIES;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            while (resultSet.next()) {
                int number = resultSet.getInt("number");
                boolean olympic = resultSet.getBoolean("olympic");

                Series s = new Series(number, olympic);
                series.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return series;
    }

    public static List<University> readUniversity() {
        List<University> universities = new ArrayList<>();
        String selectSql = "SELECT * FROM University;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");

                String[] cuv = address.split(",");
                Address a = new Address(cuv[0], cuv[1], cuv[2], Integer.parseInt(cuv[3]));

                University u = new University(name, a);
                universities.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return universities;
    }
    public static List<Subject> readSubject() {
        List<Subject> subjects = new ArrayList<>();

        String selectSql = "SELECT * FROM subject;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            while (resultSet.next()) {
                int studentId = resultSet.getInt("student");
                String name = resultSet.getString("name");
                int mark = resultSet.getInt("mark");

                Subject subject = new Subject(studentId, name, mark);
                subjects.add(subject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subjects;
    }
    public static List<Student> readStudent() {
        List<Student> students = new ArrayList<>();

        String selectSql = "SELECT * FROM STUDENT;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                LocalDate date = resultSet.getDate("birthdate").toLocalDate();
                String progstudiu = resultSet.getString("studyProgram");
                int grupa = resultSet.getInt("groupnumber");
                String uni = resultSet.getString("university");
                int serie = resultSet.getInt("serienumber");

                String[] cuv = address.split(",");
                Address a = new Address(cuv[0], cuv[1], cuv[2], Integer.parseInt(cuv[3]));
                StudyProgram dom = null;

                for (StudyProgram studyProgram : studyprograms) {
                    if (progstudiu.equals(studyProgram.getName())) {
                        dom = studyProgram;
                        break;
                    }
                }


                Group gr = null;

                for (Group group : groups) {
                    if (grupa == group.getNumber()) {
                        gr = group;
                        break;
                    }
                }

                Series ser = null;

                for (Series seri: series) {
                    if (serie == seri.getNumber()) {
                        ser = seri;
                        break;
                    }
                }

                University university = null;

                for (University u : universities) {
                    if (uni.equals(u.getName())) {
                        university = u;
                        break;
                    }
                }


                Student s = new Student(id, firstName, lastName, email, a, date, dom, gr, university, ser);

                students.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

}

