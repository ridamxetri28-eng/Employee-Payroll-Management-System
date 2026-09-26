package com.example;

public class PayrollCalculator {

    public double calculateNetSalary(double baseSalary,
                                     double bonus,
                                     double deduction){
        return baseSalary + bonus -deduction;
    }
}
