package com.example;

public interface Payable {

    double calculateNetSalary(
            double baseSalary,
            double bonus,
            double deduction
    );
}