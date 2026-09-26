package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;

public class EmployeeService {

    private ArrayList<Employee> employees;

    private HashMap<Integer, Employee> employeeMap;

    public EmployeeService() {

        employees = new ArrayList<>();

        employeeMap = new HashMap<>();
    }

    public void loadEmployees(
            ArrayList<Employee> employeeList) {

        employees.clear();

        employeeMap.clear();

        for (Employee employee : employeeList) {

            employees.add(employee);

            employeeMap.put(
                    employee.getId(),
                    employee
            );
        }
    }

    public void addEmployee(Employee employee) {

        employees.add(employee);

        employeeMap.put(
                employee.getId(),
                employee
        );
    }



    public void viewEmployees() {

        System.out.println("\n===== Employee List =====");

        for (Employee employee : employees) {

            System.out.println(
                    employee.getId() + " | " +
                            employee.getName() + " | " +
                            employee.getDepartment() + " | " +
                            employee.getPosition() + " | " +
                            employee.getBaseSalary()
            );
        }
    }



    public void sortBySalary() {

        employees.sort(
                Comparator.comparingDouble(
                        Employee::getBaseSalary
                )
        );

        System.out.println(
                "\nEmployees sorted by salary:"
        );

        for (Employee employee : employees) {

            System.out.println(
                    employee.getName() +
                            " | " +
                            employee.getBaseSalary()
            );
        }
    }



    public void filterByDepartment(
            String department) {

        System.out.println(
                "\nEmployees in " +
                        department +
                        " department:"
        );

        for (Employee employee : employees) {

            if (employee.getDepartment()
                    .equalsIgnoreCase(department)) {

                System.out.println(
                        employee.getName() +
                                " | " +
                                employee.getPosition() +
                                " | " +
                                employee.getBaseSalary()
                );
            }
        }
    }



    public Employee findById(int id) {

        return employeeMap.get(id);
    }
}