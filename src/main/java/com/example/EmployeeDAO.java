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

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getDepartment());
            statement.setString(4, employee.getPosition());
            statement.setDouble(5, employee.getBaseSalary());

            statement.executeUpdate();

            System.out.println("Employee added successfully!");

        } catch (Exception e) {

            System.out.println(
                    "Error adding employee: " +
                            e.getMessage()
            );
        }
    }



    public void viewEmployees() {

        String sql = "SELECT * FROM employees";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()
        ) {

            System.out.println("\nAll Employees:");

            while (result.next()) {

                System.out.println(
                        result.getInt("id") + " | " +
                                result.getString("name") + " | " +
                                result.getString("department") + " | " +
                                result.getString("position") + " | " +
                                result.getDouble("base_salary")
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error viewing employees: " +
                            e.getMessage()
            );
        }
    }



    public void updateEmployee(Employee employee) {

        String sql =
                "UPDATE employees SET " +
                        "name = ?, department = ?, " +
                        "position = ?, base_salary = ? " +
                        "WHERE id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, employee.getName());
            statement.setString(2, employee.getDepartment());
            statement.setString(3, employee.getPosition());
            statement.setDouble(4, employee.getBaseSalary());
            statement.setInt(5, employee.getId());

            int rows = statement.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Employee updated successfully!"
                );

            } else {

                System.out.println(
                        "Employee not found."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error updating employee: " +
                            e.getMessage()
            );
        }
    }



    public void deleteEmployee(int id) {

        String sql =
                "DELETE FROM employees WHERE id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            int rows = statement.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Employee deleted successfully!"
                );

            } else {

                System.out.println(
                        "Employee not found."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error deleting employee: " +
                            e.getMessage()
            );
        }
    }



    public Employee searchEmployee(int id)
            throws EmployeeNotFoundException {

        String sql =
                "SELECT * FROM employees WHERE id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {

                    return new Employee(
                            result.getInt("id"),
                            result.getString("name"),
                            result.getString("department"),
                            result.getString("position"),
                            result.getDouble("base_salary")
                    );
                }
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error searching employee: " +
                            e.getMessage()
            );
        }

        throw new EmployeeNotFoundException(
                "Employee with ID " + id +
                        " was not found."
        );
    }


    public void viewEmployeesByDepartment(
            String department) {

        String sql =
                "SELECT * FROM employees " +
                        "WHERE department = ?";

        double totalSalary = 0;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, department);

            ResultSet result =
                    statement.executeQuery();

            boolean found = false;

            System.out.println(
                    "\n===== " +
                            department +
                            " Department ====="
            );

            while (result.next()) {

                found = true;

                double salary =
                        result.getDouble("base_salary");

                totalSalary += salary;

                System.out.println(
                        result.getInt("id") + " | " +
                                result.getString("name") + " | " +
                                result.getString("position") + " | " +
                                salary
                );
            }

            if (!found) {

                System.out.println(
                        "No employees found."
                );

            } else {

                System.out.println(
                        "Total Salary: " +
                                totalSalary
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error finding department employees: " +
                            e.getMessage()
            );
        }

    }

    public java.util.ArrayList<Employee> getAllEmployees() {

        java.util.ArrayList<Employee> employees =
                new java.util.ArrayList<>();

        String sql = "SELECT * FROM employees";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()
        ) {

            while (result.next()) {

                Employee employee = new Employee(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("department"),
                        result.getString("position"),
                        result.getDouble("base_salary")
                );

                employees.add(employee);
            }

        } catch (Exception e) {

            System.out.println(
                    "Error loading employees: " +
                            e.getMessage()
            );
        }

        return employees;
    }
}