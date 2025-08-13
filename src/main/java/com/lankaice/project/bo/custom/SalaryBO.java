package com.lankaice.project.bo.custom;

import com.lankaice.project.bo.SuperBO;
import com.lankaice.project.dto.SalaryDto;

import java.sql.SQLException;
import java.util.List;

public interface SalaryBO extends SuperBO {

    List<SalaryDto> getAllSalaries() throws SQLException, ClassNotFoundException;

    boolean updateSalary(SalaryDto dto) throws SQLException, ClassNotFoundException;

    String getEmployeeRole(String employeeId) throws SQLException, ClassNotFoundException;

    double calculateDeduction(String employeeId, int month, int year) throws SQLException, ClassNotFoundException;

    double getWorkingHours(String employeeId, int month, int year) throws SQLException, ClassNotFoundException;

    boolean deleteSalary(String employeeId, int payMonth, int payYear) throws SQLException, ClassNotFoundException;

    boolean updateSalaryStatus(int salaryId, String newStatus) throws SQLException, ClassNotFoundException;

    boolean generateMonthlySalaries(int month, int year) throws SQLException, ClassNotFoundException;

    boolean deleteAllSalary() throws SQLException, ClassNotFoundException;
}
