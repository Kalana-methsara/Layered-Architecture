package com.lankaice.project.dao.custom;

import com.lankaice.project.dao.CrudDAO;
import com.lankaice.project.entity.Salary;

import java.sql.SQLException;

public interface SalaryDAO extends CrudDAO<Salary> {
    String getEmployeeRole(String employeeId) throws SQLException, ClassNotFoundException;

    double calculateDeduction(String employeeId, int month, int year) throws SQLException, ClassNotFoundException;

    double getWorkingHours(String employeeId, int month, int year) throws SQLException, ClassNotFoundException;

    boolean deleteSalary(String employeeId, int payMonth, int payYear) throws SQLException, ClassNotFoundException;

    boolean updateSalaryStatus(int salaryId, String newStatus);

    boolean generateMonthlySalaries(int month, int year) throws SQLException, ClassNotFoundException;

    boolean deleteAllSalary() throws SQLException, ClassNotFoundException;
}
