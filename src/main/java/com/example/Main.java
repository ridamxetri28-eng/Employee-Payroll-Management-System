package com.example;


public class Main {

    public static void main(String[] args) {

        EmployeeDAO dao = new EmployeeDAO();

        Employee employee1 = new Employee(


                1,
                "Ram",
                "IT",
                "Developer",
                40000
        );

        Employee employee2 = new Employee(
                2,
                "Sita",
                "HR",
                "Manager",
                50000
        );

        Employee employee3 = new Employee(
                3,
                "Hari",
                "Finance",
                "Accountant",
                45000
        );

        dao.addEmployee(employee1);
        dao.addEmployee(employee2);
        dao.addEmployee(employee3);

        System.out.println("\nAll Employees:");


        dao.viewEmployees();

        PayrollCalculator payroll = new PayrollCalculator();

        double bonus = 5000;
        double deduction = 2000;

        double netSalary = payroll.calculateNetSalary(
                employee1.getBaseSalary(),
                bonus,
                deduction
        );

        System.out.println("\nPayroll Details:");
        System.out.println("Employee: " + employee1.getName());
        System.out.println("Base Salary: " + employee1.getBaseSalary());
        System.out.println("Bonus: " + bonus);
        System.out.println("Deduction: " + deduction);
        System.out.println("Net Salary: " + netSalary);
    }
}