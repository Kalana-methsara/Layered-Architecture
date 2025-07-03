package com.lankaice.project.dao.custom;

import com.lankaice.project.dao.CrudDAO;
import com.lankaice.project.dto.EmployeeDto;
import com.lankaice.project.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeDAO extends CrudDAO<Employee> {
    Employee searchById(String employeeId) throws SQLException, ClassNotFoundException;

    List<Employee> searchEmployee(String employeeId, String name, String nic, String contact, String email, String jobRole, String address, String joinDate, String dateOfBirth, String gender, String bankAccountNo, String bankBranch, String licenseNumber) throws ClassNotFoundException, SQLException;

    int getEmployeeCount() throws SQLException, ClassNotFoundException;

    List<Employee> searchByName(String name) throws SQLException, ClassNotFoundException;

    String getLastEmployeeId() throws SQLException, ClassNotFoundException;

    boolean isLicenseExists(String license) throws SQLException, ClassNotFoundException;

    int getEmployeeCountByRole(String role) throws SQLException, ClassNotFoundException;
    }
