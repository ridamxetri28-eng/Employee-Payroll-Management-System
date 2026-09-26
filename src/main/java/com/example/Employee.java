package com.example;

public class Employee extends Person implements Payable {

    private String department;
    private String position;
    private double baseSalary;

    public Employee(
            int id,
            String name,
            String department,
            String position,
            double baseSalary) {

        super(id, name);

        this.department = department;
        this.position = position;
        this.baseSalary = baseSalary;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public void displayRole() {
        System.out.println(
                getName() + " works in " +
                        department + " department."
        );
    }

    @Override
    public double calculateNetSalary(
            double baseSalary,
            double bonus,
            double deduction) {

        return baseSalary + bonus - deduction;
    }
}