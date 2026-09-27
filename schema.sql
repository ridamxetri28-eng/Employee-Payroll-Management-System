CREATE DATABASE employee_payroll;

USE employee_payroll;

CREATE TABLE employees (
                           id INT PRIMARY KEY,
                           name VARCHAR(100) NOT NULL,
                           department VARCHAR(100) NOT NULL,
                           position VARCHAR(100) NOT NULL,
                           base_salary DOUBLE NOT NULL
);