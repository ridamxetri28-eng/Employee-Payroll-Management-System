package com.example;

import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDAO {

    public void addEmployee(Employee employee) {

        String sql = "INSERT INTO employees " +
                "(id, name, department, position, base_salary) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {
            Connection connection = DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getDepartment());
            statement.setString(4, employee.getPosition());
            statement.setDouble(5, employee.getBaseSalary());

            statement.executeUpdate();

            System.out.println("Employee added successfully!");

            connection.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void viewEmployees() {

        String sql = "SELECT * FROM employees";

        try {
            Connection connection = DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                System.out.println(
                        result.getInt("id") + " | " +
                                result.getString("name") + " | " +
                                result.getString("department") + " | " +
                                result.getString("position") + " | " +
                                result.getDouble("base_salary")
                );
            }

            connection.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}