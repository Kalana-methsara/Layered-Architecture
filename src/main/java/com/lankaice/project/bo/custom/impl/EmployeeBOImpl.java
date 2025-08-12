package com.lankaice.project.bo.custom.impl;

import com.lankaice.project.bo.custom.EmployeeBO;
import com.lankaice.project.bo.exception.DuplicateException;
import com.lankaice.project.bo.exception.InUseException;
import com.lankaice.project.dao.DAOFactory;
import com.lankaice.project.dao.custom.EmployeeDAO;
import com.lankaice.project.dao.util.DAOFactoryImpl;
import com.lankaice.project.dao.util.DAOType;
import com.lankaice.project.dto.EmployeeDto;
import com.lankaice.project.entity.Employee;
import com.lankaice.project.bo.util.EntityDTOConverter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {

    private final EmployeeDAO employeeDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.EMPLOYEE);
    private final EntityDTOConverter converter = new EntityDTOConverter();

    @Override
    public List<EmployeeDto> getAllEmployees() throws SQLException, ClassNotFoundException {
        List<Employee> employees = employeeDAO.getAll();
        List<EmployeeDto> dtoList = new ArrayList<>();
        for (Employee employee : employees) {
            dtoList.add(converter.getEmployeeDto(employee));
        }
        return dtoList;
    }

    @Override
    public void saveEmployee(EmployeeDto dto) throws DuplicateException, Exception {
        if (employeeDAO.findById(dto.getEmployeeId()).isPresent()) {
            throw new DuplicateException("Employee with ID " + dto.getEmployeeId() + " already exists.");
        }
        boolean saved = employeeDAO.save(converter.getEmployee(dto));
        if (!saved) {
            throw new Exception("Failed to save employee.");
        }
    }

    @Override
    public void updateEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        employeeDAO.update(converter.getEmployee(dto));
    }

    @Override
    public void deleteEmployee(String id) throws InUseException, Exception {
        if (!employeeDAO.delete(id)) {
            throw new Exception("Failed to delete employee with ID: " + id);
        }
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String lastId = employeeDAO.getLastEmployeeId();
        if (lastId == null || lastId.isEmpty()) {
            return "E001";
        }
        int num = Integer.parseInt(lastId.substring(1)) + 1;
        return String.format("E%03d", num);
    }

    @Override
    public int getEmployeeCount() throws SQLException, ClassNotFoundException {
        return employeeDAO.getEmployeeCount();
    }

    @Override
    public String getLastEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getLastEmployeeId();
    }

    @Override
    public List<EmployeeDto> searchEmployee(String employeeId, String name, String nic, String contact, String email,
                                            String jobRole, String address, String joinDate, String dateOfBirth,
                                            String gender, String bankAccountNo, String bankBranch, String licenseNumber)
            throws SQLException, ClassNotFoundException {
        List<Employee> employees = employeeDAO.searchEmployee(employeeId, name, nic, contact, email,
                jobRole, address, joinDate, dateOfBirth, gender, bankAccountNo, bankBranch, licenseNumber);
        List<EmployeeDto> dtoList = new ArrayList<>();
        for (Employee employee : employees) {
            dtoList.add(converter.getEmployeeDto(employee));
        }
        return dtoList;
    }

    @Override
    public String findNameById(String employeeId) throws SQLException, ClassNotFoundException {
        return employeeDAO.findNameById(employeeId);
    }

    @Override
    public boolean isLicenseExists(String license) throws SQLException, ClassNotFoundException {
        return employeeDAO.isLicenseExists(license);
    }

    @Override
    public int getEmployeeCountByRole(String role) throws SQLException, ClassNotFoundException {
        return employeeDAO.getEmployeeCountByRole(role);
    }

    @Override
    public List<EmployeeDto> searchByName(String name) throws SQLException, ClassNotFoundException {
        List<Employee> employees = employeeDAO.searchByName(name);
        List<EmployeeDto> dtoList = new ArrayList<>();
        for (Employee employee : employees) {
            dtoList.add(converter.getEmployeeDto(employee));
        }
        return dtoList;
    }
}
