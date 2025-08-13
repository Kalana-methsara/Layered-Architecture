package com.lankaice.project.bo.custom.impl;

import com.lankaice.project.bo.custom.SalaryBO;
import com.lankaice.project.dao.custom.SalaryDAO;
import com.lankaice.project.dao.util.DAOFactoryImpl;
import com.lankaice.project.dao.util.DAOType;
import com.lankaice.project.dto.SalaryDto;
import com.lankaice.project.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryBOImpl implements SalaryBO {

    private final SalaryDAO salaryDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.SALARY);

    @Override
    public List<SalaryDto> getAllSalaries() throws SQLException, ClassNotFoundException {
        List<Salary> salaries = salaryDAO.getAll();
        List<SalaryDto> dtoList = new ArrayList<>();

        for (Salary s : salaries) {
            dtoList.add(new SalaryDto(
                    s.getSalaryId(),
                    s.getEmployeeId(),
                    s.getEmployeeName(),
                    s.getBasicSalary(),
                    s.getBonus(),
                    s.getDeduction(),
                    s.getNetSalary(),
                    s.getPayMonth(),
                    s.getPayYear(),
                    s.getStatus()
            ));
        }
        return dtoList;
    }

    @Override
    public boolean updateSalary(SalaryDto dto) throws SQLException, ClassNotFoundException {
        return salaryDAO.update(new Salary(
                dto.getSalaryId(),
                dto.getEmployeeId(),
                dto.getEmployeeName(),
                dto.getBasicSalary(),
                dto.getBonus(),
                dto.getDeduction(),
                dto.getNetSalary(),
                dto.getPayMonth(),
                dto.getPayYear(),
                dto.getStatus()
        ));
    }

    @Override
    public String getEmployeeRole(String employeeId) throws SQLException, ClassNotFoundException {
        return salaryDAO.getEmployeeRole(employeeId);
    }

    @Override
    public double calculateDeduction(String employeeId, int month, int year) throws SQLException, ClassNotFoundException {
        return salaryDAO.calculateDeduction(employeeId, month, year);
    }

    @Override
    public double getWorkingHours(String employeeId, int month, int year) throws SQLException, ClassNotFoundException {
        return salaryDAO.getWorkingHours(employeeId, month, year);
    }

    @Override
    public boolean deleteSalary(String employeeId, int payMonth, int payYear) throws SQLException, ClassNotFoundException {
        return salaryDAO.deleteSalary(employeeId, payMonth, payYear);
    }

    @Override
    public boolean updateSalaryStatus(int salaryId, String newStatus) throws SQLException, ClassNotFoundException {
        return salaryDAO.updateSalaryStatus(salaryId, newStatus);
    }

    @Override
    public boolean generateMonthlySalaries(int month, int year) throws SQLException, ClassNotFoundException {
        return salaryDAO.generateMonthlySalaries(month, year);
    }

    @Override
    public boolean deleteAllSalary() throws SQLException, ClassNotFoundException {
        return salaryDAO.deleteAllSalary();
    }
}
