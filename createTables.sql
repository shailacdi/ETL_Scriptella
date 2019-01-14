#creates new database
CREATE DATABASE if NOT exists company;

#use database
USE company;

#drop employee if it exists
DROP TABLE if exists employee ;

#drop department if it exists
DROP TABLE if exists department;

#drop import log if it exists
DROP TABLE if exists data_import_logs;

#drop import log if it exists
DROP TABLE if exists employee_unnormalized;

#creates new table employee_unnormalized to store unnomarlized data from .tsv file
CREATE TABLE if NOT exists employee_unnormalized (
    employee_id INT PRIMARY KEY,
    employee_last_name VARCHAR(50),
    employee_first_name VARCHAR(50) NOT NULL,
    department_id INT NOT NULL,
    department_name VARCHAR(100) NOT NULL
);

#creates new table for Department
CREATE TABLE department (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(100) NOT NULL
);

#creates new table for Employee
CREATE TABLE employee (
    employee_id INT PRIMARY KEY,
    employee_last_name VARCHAR(50),
    employee_first_name VARCHAR(50) NOT NULL,
    department_id INT NOT NULL,
    FOREIGN KEY (department_id) REFERENCES department(department_id)
);

CREATE TABLE data_import_logs(
    id INT AUTO_INCREMENT PRIMARY KEY,
    message VARCHAR(200)
)