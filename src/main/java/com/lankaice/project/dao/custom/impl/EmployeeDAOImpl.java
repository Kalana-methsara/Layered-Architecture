package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.EmployeeDAO;
import com.lankaice.project.dao.util.SQLUtil;
import com.lankaice.project.dto.EmployeeDto;
import com.lankaice.project.dto.OrderPaymentDto;
import com.lankaice.project.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public List<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Employee");
        List<Employee> employee = new ArrayList<>();
        while (resultSet.next()) {
            employee.add(new Employee(
                    resultSet.getString("employee_id"),
                    resultSet.getString("name"),
                    resultSet.getString("nic"),
                    resultSet.getString("contact"),
                    resultSet.getString("email"),
                    resultSet.getString("job_role"),
                    resultSet.getString("address"),
                    resultSet.getString("join_date"),
                    resultSet.getString("date_of_birth"),
                    resultSet.getString("gender"),
                    resultSet.getString("bank_account_no"),
                    resultSet.getString("bank_branch"),
                    resultSet.getString("license_number")
            ));
        }
        return employee;
    }


    @Override
    public boolean save(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Employee (employee_id,name,nic,contact,email,job_role,address,join_date,date_of_birth,gender,bank_account_no,bank_branch,license_number) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return SQLUtil.execute(sql, employee.getEmployeeId(), employee.getName(), employee.getNic(), employee.getContact(), employee.getEmail(), employee.getJobRole(), employee.getAddress(), employee.getJoinDate(), employee.getDateOfBirth(), employee.getGender(), employee.getBankAccountNo(), employee.getBankBranch(), employee.getLicenseNumber());
    }

    @Override
    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Employee SET name = ?, nic = ? ,contact= ? ,email= ? ,job_role= ? ,address= ? ,join_date= ? ,date_of_birth = ?,gender = ?,bank_account_no= ? ,bank_branch= ? ,license_number= ? WHERE employee_id = ?";
        return SQLUtil.execute(sql, employee.getName(), employee.getNic(), employee.getContact(), employee.getEmail(), employee.getJobRole(), employee.getAddress(), employee.getJoinDate(), employee.getDateOfBirth(), employee.getGender(), employee.getBankAccountNo(), employee.getBankBranch(), employee.getLicenseNumber(), employee.getEmployeeId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Employee WHERE employee_id = ?";
        return SQLUtil.execute(sql, id);
    }

    @Override
    public Optional<Employee> findById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Employee WHERE employee_id = ?", id);
        if (resultSet.next()) {
            return Optional.of(new Employee(
                    resultSet.getString("employee_id"),
                    resultSet.getString("name"),
                    resultSet.getString("nic"),
                    resultSet.getString("contact"),
                    resultSet.getString("email"),
                    resultSet.getString("job_role"),
                    resultSet.getString("address"),
                    resultSet.getString("join_date"),
                    resultSet.getString("date_of_birth"),
                    resultSet.getString("gender"),
                    resultSet.getString("bank_account_no"),
                    resultSet.getString("bank_branch"),
                    resultSet.getString("license_number")
            ));
        }
        return Optional.empty();
    }

    @Override
    public Employee searchById(String employeeId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Employee WHERE employee_id = ?";
        ResultSet resultSet = SQLUtil.execute(sql, employeeId);

        if (resultSet.next()) {
            return new Employee(
                    resultSet.getString("employee_id"),
                    resultSet.getString("name"),
                    resultSet.getString("nic"),
                    resultSet.getString("contact"),
                    resultSet.getString("email"),
                    resultSet.getString("job_role"),
                    resultSet.getString("address"),
                    resultSet.getString("join_date"),
                    resultSet.getString("date_of_birth"),
                    resultSet.getString("gender"),
                    resultSet.getString("bank_account_no"),
                    resultSet.getString("bank_branch"),
                    resultSet.getString("license_number")
            );
        }
        return null;
    }

    @Override
    public List<Employee> searchEmployee(String employeeId, String name, String nic, String contact, String email, String jobRole, String address, String joinDate, String dateOfBirth, String gender, String bankAccountNo, String bankBranch, String licenseNumber) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Employee WHERE " +
                "employee_id LIKE ? OR name LIKE ? OR nic LIKE ? OR contact LIKE ? OR email LIKE ? OR " +
                "job_role LIKE ? OR address LIKE ? OR join_date LIKE ? OR date_of_birth LIKE ? OR " +
                "gender LIKE ? OR bank_account_no LIKE ? OR bank_branch LIKE ? OR license_number LIKE ?";

        ResultSet resultSet = SQLUtil.execute(sql,
                "%" + employeeId + "%", "%" + name + "%", "%" + nic + "%", "%" + contact + "%", "%" + email + "%",
                "%" + jobRole + "%", "%" + address + "%", "%" + joinDate + "%", "%" + dateOfBirth + "%",
                "%" + gender + "%", "%" + bankAccountNo + "%", "%" + bankBranch + "%", "%" + licenseNumber + "%");

        List<Employee> employee = new ArrayList<>();
        while (resultSet.next()) {
            employee.add(new Employee(
                    resultSet.getString("employee_id"),
                    resultSet.getString("name"),
                    resultSet.getString("nic"),
                    resultSet.getString("contact"),
                    resultSet.getString("email"),
                    resultSet.getString("job_role"),
                    resultSet.getString("address"),
                    resultSet.getString("join_date"),
                    resultSet.getString("date_of_birth"),
                    resultSet.getString("gender"),
                    resultSet.getString("bank_account_no"),
                    resultSet.getString("bank_branch"),
                    resultSet.getString("license_number")
            ));
        }
        return employee;
    }

    @Override
    public int getEmployeeCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) FROM Employee");
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    @Override
    public List<Employee> searchByName(String name) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Employee WHERE name LIKE ?", "%" + name + "%");
        List<Employee> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new Employee(
                    resultSet.getString("employee_id"),
                    resultSet.getString("name"),
                    resultSet.getString("nic"),
                    resultSet.getString("contact"),
                    resultSet.getString("email"),
                    resultSet.getString("job_role"),
                    resultSet.getString("address"),
                    resultSet.getString("join_date"),
                    resultSet.getString("date_of_birth"),
                    resultSet.getString("gender"),
                    resultSet.getString("bank_account_no"),
                    resultSet.getString("bank_branch"),
                    resultSet.getString("license_number")
            ));
        }
        return list;
    }

    @Override
    public String getLastEmployeeId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT MAX(employee_id) FROM Employee");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public boolean isLicenseExists(String license) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT licenseNumber FROM Employee WHERE licenseNumber = ?", license);
        return rs.next();
    }

    @Override
    public int getEmployeeCountByRole(String role) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM Employee WHERE job_role = ?";
        ResultSet result = SQLUtil.execute(sql, role);
        if (result.next()) {
            return result.getInt(1);
        }
        return 0;    }

    @Override
    public String findNameById(String employeeId) throws SQLException, ClassNotFoundException{
        ResultSet rst = SQLUtil.execute(
                "SELECT name FROM Employee WHERE employee_id=?",
                employeeId
        );
        if (rst.next()) {
            return rst.getString("name");
        }
        return "";
    }


}
