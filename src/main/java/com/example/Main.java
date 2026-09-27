package com.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        EmployeeDAO dao = new EmployeeDAO();
        PayrollCalculator payroll =
                new PayrollCalculator();

        EmployeeService service =
                new EmployeeService();

        service.loadEmployees(
                dao.getAllEmployees()
        );

        boolean running = true;



        while (running) {

            System.out.println("\n======================================");
            System.out.println(" Employee Payroll Management System");
            System.out.println("======================================");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Search Employee");
            System.out.println("6. Calculate Payroll");
            System.out.println("7. Department Salary Summary");
            System.out.println("8. Sort Employees by Salary");
            System.out.println("9. Filter Employees by Department");
            System.out.println("10. Search Employee Using Collection");
            System.out.println("11. Exit");
            System.out.println("======================================");

            int choice = readInt(
                    scanner,
                    "Enter your choice: "
            );

            switch (choice) {

                case 1:

                    addEmployee(scanner, dao);

                    service.loadEmployees(
                            dao.getAllEmployees()
                    );

                    break;
                case 2:
                    dao.viewEmployees();
                    break;

                case 3:

                    updateEmployee(scanner, dao);

                    service.loadEmployees(
                            dao.getAllEmployees()
                    );

                    break;

                case 4:

                    deleteEmployee(scanner, dao);

                    service.loadEmployees(
                            dao.getAllEmployees()
                    );

                    break;

                case 5:
                    searchEmployee(scanner, dao);
                    break;

                case 6:
                    calculatePayroll(
                            scanner,
                            dao,
                            payroll
                    );
                    break;

                case 7:
                    departmentSummary(scanner, dao);
                    break;

                case 8:

                    service.sortBySalary();

                    break;

                case 9:

                    String department =
                            readText(
                                    scanner,
                                    "Enter department: "
                            );

                    service.filterByDepartment(
                            department
                    );

                    break;

                case 10:

                    int collectionId =
                            readInt(
                                    scanner,
                                    "Enter employee ID: "
                            );

                    Employee collectionEmployee =
                            service.findById(collectionId);

                    if (collectionEmployee == null) {

                        System.out.println(
                                "Employee not found in collection."
                        );

                    } else {

                        System.out.println(
                                "Employee found using HashMap:"
                        );

                        System.out.println(
                                "ID: " +
                                        collectionEmployee.getId()
                        );

                        System.out.println(
                                "Name: " +
                                        collectionEmployee.getName()
                        );

                        System.out.println(
                                "Department: " +
                                        collectionEmployee.getDepartment()
                        );

                    }

                    break;

                case 11:

                    running = false;

                    System.out.println(
                            "Thank you for using the system!"
                    );

                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please choose 1-10."
                    );
            }
        }

        scanner.close();
    }



    private static void addEmployee(
            Scanner scanner,
            EmployeeDAO dao) {

        System.out.println("\n===== Add Employee =====");

        int id = readInt(
                scanner,
                "Enter employee ID: "
        );

        String name = readText(
                scanner,
                "Enter employee name: "
        );

        String department = readText(
                scanner,
                "Enter department: "
        );

        String position = readText(
                scanner,
                "Enter position: "
        );

        double salary = readDouble(
                scanner,
                "Enter base salary: "
        );

        Employee employee = new Employee(
                id,
                name,
                department,
                position,
                salary
        );

        dao.addEmployee(employee);
    }



    private static void updateEmployee(
            Scanner scanner,
            EmployeeDAO dao) {

        System.out.println("\n===== Update Employee =====");

        int id = readInt(
                scanner,
                "Enter employee ID to update: "
        );

        Employee existingEmployee;

        try {

            existingEmployee =
                    dao.searchEmployee(id);

        } catch (EmployeeNotFoundException e) {

            System.out.println(
                    e.getMessage()
            );

            return;
        }

        System.out.println(
                "Current employee: " +
                        existingEmployee.getName()
        );

        String name = readText(
                scanner,
                "Enter new name: "
        );

        String department = readText(
                scanner,
                "Enter new department: "
        );

        String position = readText(
                scanner,
                "Enter new position: "
        );

        double salary = readDouble(
                scanner,
                "Enter new base salary: "
        );

        Employee employee = new Employee(
                id,
                name,
                department,
                position,
                salary
        );

        dao.updateEmployee(employee);
    }



    private static void deleteEmployee(
            Scanner scanner,
            EmployeeDAO dao) {

        System.out.println("\n===== Delete Employee =====");

        int id = readInt(
                scanner,
                "Enter employee ID to delete: "
        );

        dao.deleteEmployee(id);
    }



    private static void searchEmployee(
            Scanner scanner,
            EmployeeDAO dao) {

        System.out.println("\n===== Search Employee =====");

        int id = readInt(
                scanner,
                "Enter employee ID: "
        );

        try {

            Employee employee =
                    dao.searchEmployee(id);

            System.out.println("\nEmployee Found:");

            System.out.println(
                    "ID: " + employee.getId()
            );

            System.out.println(
                    "Name: " + employee.getName()
            );

            System.out.println(
                    "Department: " +
                            employee.getDepartment()
            );

            System.out.println(
                    "Position: " +
                            employee.getPosition()
            );

            System.out.println(
                    "Base Salary: " +
                            employee.getBaseSalary()
            );

        } catch (EmployeeNotFoundException e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }



    private static void calculatePayroll(
            Scanner scanner,
            EmployeeDAO dao,
            PayrollCalculator payroll) {

        System.out.println("\n===== Calculate Payroll =====");

        int id = readInt(
                scanner,
                "Enter employee ID: "
        );

        Employee employee;

        try {

            employee = dao.searchEmployee(id);

        } catch (EmployeeNotFoundException e) {

            System.out.println(
                    e.getMessage()
            );

            return;
        }


        String month = readText(
                scanner,
                "Enter month: "
        );


        double bonus = readDouble(
                scanner,
                "Enter bonus: "
        );


        double deduction = readDouble(
                scanner,
                "Enter deduction: "
        );


        payroll.printPayslip(
                employee,
                bonus,
                deduction,
                month
        );
    }



    private static void departmentSummary(
            Scanner scanner,
            EmployeeDAO dao) {

        System.out.println(
                "\n===== Department Salary Summary ====="
        );

        String department = readText(
                scanner,
                "Enter department: "
        );

        dao.viewEmployeesByDepartment(
                department
        );
    }



    private static int readInt(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }



    private static double readDouble(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            try {

                double value =
                        Double.parseDouble(input);

                if (value < 0) {

                    System.out.println(
                            "Value cannot be negative."
                    );

                    continue;
                }

                return value;

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }



    private static String readText(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            if (input.isEmpty()) {

                System.out.println(
                        "Input cannot be empty."
                );

            } else {

                return input;
            }
        }
    }
}