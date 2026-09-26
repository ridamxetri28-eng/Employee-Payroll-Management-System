package com.example;

public class Employee {

    private int id;
    private String name;
    private String department;
    private String position;
    private double baseSalary;

    public Employee(int id, String name, String department,
                    String position, double baseSalary) {

        this.id = id;
        this.name = name;
        this.department = department;
        this.position = position;
        this.baseSalary = baseSalary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
}