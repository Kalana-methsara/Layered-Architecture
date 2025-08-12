package com.lankaice.project.bo.custom;

import com.lankaice.project.bo.SuperBO;
import com.lankaice.project.bo.exception.DuplicateException;
import com.lankaice.project.bo.exception.InUseException;
import com.lankaice.project.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {

    List<EmployeeDto> getAllEmployees() throws SQLException, ClassNotFoundException;

    void saveEmployee(EmployeeDto dto) throws DuplicateException, Exception;

    void updateEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException;

    void deleteEmployee(String id) throws InUseException, Exception;

    String getNextId() throws SQLException, ClassNotFoundException;

    int getEmployeeCount() throws SQLException, ClassNotFoundException;

    String getLastEmployeeId() throws SQLException, ClassNotFoundException;

    List<EmployeeDto> searchEmployee(String employeeId, String name, String nic, String contact, String email,
                                     String jobRole, String address, String joinDate, String dateOfBirth,
                                     String gender, String bankAccountNo, String bankBranch, String licenseNumber)
            throws SQLException, ClassNotFoundException;

    String findNameById(String employeeId) throws SQLException, ClassNotFoundException;

    boolean isLicenseExists(String license) throws SQLException, ClassNotFoundException;

    int getEmployeeCountByRole(String role) throws SQLException, ClassNotFoundException;

    List<EmployeeDto> searchByName(String name) throws SQLException, ClassNotFoundException;
}
