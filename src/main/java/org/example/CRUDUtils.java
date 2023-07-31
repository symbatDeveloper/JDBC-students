package org.example;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;

public class CRUDUtils {
    private static String INSERT_STUDENT = "INSERT INTO students(name) VALUES(?)";
    private static String UPDATE_STUDENT = "UPDATE students set id=?";
    private static String DELETE_STUDENT = "DELETE FROM students WHERE id=?";

    public static List<Student> getStudentData(String query) {
        List<Student> students = new ArrayList<>();
        try (Connection connection = JDBC.getConnaection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getNString("name");

                students.add(new Student(id, name));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public static List<Student> saveStudent(Student student) {
        List<Student> students = new ArrayList<>();
        try (Connection connection = JDBC.getConnaection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getName());
            preparedStatement.executeUpdate();

            // to check after adding the new students
            PreparedStatement allStudents = connection.prepareStatement("SELECT * from students");
            ResultSet rs = allStudents.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getNString("name");

                students.add(new Student(id, name));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public static List<Student> updateStudent(int studentId) {
        List<Student> updateStudents = new ArrayList<>();
        try (Connection connection = JDBC.getConnaection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();

        }
        return updateStudents;

    }
    public static void deleteStudent(int studentId) {
        try (Connection connection = JDBC.getConnaection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();

        }
        getStudentData("SELECT * FROM students");
    }
}
