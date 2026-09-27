package com.example;

public class PayrollCalculator {

    public double calculateNetSalary(double baseSalary,
                                     double bonus,
                                     double deduction){
        return baseSalary + bonus -deduction;
    }

    public void printPayslip(
            Employee employee,
            double bonus,
            double deduction,
            String month) {

        double netSalary = calculateNetSalary(
                employee.getBaseSalary(),
                bonus,
                deduction
        );

        System.out.println();
        System.out.println("================================");
        System.out.println("             PAYSLIP");
        System.out.println("================================");

        System.out.println(
                "Month       : " + month
        );

        System.out.println(
                "Employee ID : " + employee.getId()
        );

        System.out.println(
                "Name        : " + employee.getName()
        );

        System.out.println(
                "Department  : " + employee.getDepartment()
        );

        System.out.println(
                "Position    : " + employee.getPosition()
        );

        System.out.println("--------------------------------");

        System.out.println(
                "Base Salary : " + employee.getBaseSalary()
        );

        System.out.println(
                "Bonus       : " + bonus
        );

        System.out.println(
                "Deduction   : " + deduction
        );

        System.out.println("--------------------------------");

        System.out.println(
                "Net Salary  : " + netSalary
        );

        System.out.println("================================");
    }
}
