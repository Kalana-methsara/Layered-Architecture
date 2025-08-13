package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.SalaryDAO;
import com.lankaice.project.dao.util.SQLUtil;
import com.lankaice.project.db.DBConnection;
import com.lankaice.project.dto.OrderPaymentDto;
import com.lankaice.project.entity.Salary;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public List<Salary> getAll() throws SQLException, ClassNotFoundException {
        String sql = """
            SELECT s.salary_id, s.employee_id, e.name, s.basic_amount, s.bonus, 
                   s.deduction, s.net_amount, s.pay_month, s.pay_year, s.status 
            FROM Salary s 
            INNER JOIN Employee e ON s.employee_id = e.employee_id
        """;

        ResultSet rs = SQLUtil.execute(sql);
        ArrayList<Salary> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Salary(
                    rs.getInt("salary_id"),
                    rs.getString("employee_id"),
                    rs.getString("name"),
                    rs.getDouble("basic_amount"),
                    rs.getDouble("bonus"),
                    rs.getDouble("deduction"),
                    rs.getDouble("net_amount"),
                    rs.getInt("pay_month"),
                    rs.getInt("pay_year"),
                    rs.getString("status")
            ));
        }
        return list;    }

    @Override
    public boolean save(Salary salary) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Salary salary) throws SQLException, ClassNotFoundException {
        String sql = """
            UPDATE Salary 
            SET basic_amount = ?, bonus = ?, deduction = ?, net_amount = ?, status = ? 
            WHERE employee_id = ? AND pay_month = ? AND pay_year = ?
        """;
        return SQLUtil.execute(sql,
                salary.getBasicSalary(),
                salary.getBonus(),
                salary.getDeduction(),
                salary.getNetSalary(),
                salary.getStatus(),
                salary.getEmployeeId(),
                salary.getPayMonth(),
                salary.getPayYear()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<Salary> findById(String id) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public String getEmployeeRole(String employeeId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT job_role FROM Employee WHERE employee_id = ?";
        ResultSet rs = SQLUtil.execute(sql, employeeId);
        return rs.next() ? rs.getString("job_role") : null;
    }

    @Override
    public double calculateDeduction(String employeeId, int month, int year) throws SQLException, ClassNotFoundException {
        String sql = """
            SELECT COUNT(*) AS absent_days 
            FROM Attendance 
            WHERE employee_id = ? AND MONTH(date) = ? AND YEAR(date) = ? AND status = 'Absent'
        """;

        try (PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            stmt.setString(1, employeeId);
            stmt.setInt(2, month);
            stmt.setInt(3, year);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int absentDays = rs.getInt("absent_days");
                return absentDays * 100.0;
            }
        }
        return 0;
    }

    @Override
    public double getWorkingHours(String employeeId, int month, int year) throws SQLException, ClassNotFoundException {
        String sql = """
            SELECT in_time, out_time 
            FROM Attendance 
            WHERE employee_id = ? AND MONTH(date) = ? AND YEAR(date) = ? AND status = 'Present'
        """;

        double totalHours = 0;

        try (PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            stmt.setString(1, employeeId);
            stmt.setInt(2, month);
            stmt.setInt(3, year);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Time in = rs.getTime("in_time");
                Time out = rs.getTime("out_time");
                if (in != null && out != null) {
                    long millis = out.getTime() - in.getTime();
                    totalHours += millis / (1000.0 * 60 * 60); // ms → hrs
                }
            }
        }

        return totalHours;
    }

    @Override
    public boolean deleteSalary(String employeeId, int payMonth, int payYear) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Salary WHERE employee_id = ? AND pay_month = ? AND pay_year = ?";
        return SQLUtil.execute(sql, employeeId, payMonth, payYear);
    }

    @Override
    public boolean updateSalaryStatus(int salaryId, String newStatus) {
        String sql = "UPDATE Salary SET status = ? WHERE salary_id = ?";
        try {
            return SQLUtil.execute(sql, newStatus, salaryId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean generateMonthlySalaries(int month, int year) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT employee_id FROM Employee ORDER BY employee_id ASC");

        boolean atLeastOneGenerated = false;

        while (rs.next()) {
            String empId = rs.getString("employee_id");
            String role = getEmployeeRole(empId);
            if (role == null) continue;

            double hourlyRate;
            double bonus;

            switch (role) {
                case "Manager" -> { hourlyRate = 250; bonus = 5000; }
                case "Supervisor" -> { hourlyRate = 200; bonus = 3000; }
                case "Cashier" -> { hourlyRate = 180; bonus = 2000; }
                case "Driver" -> { hourlyRate = 175; bonus = 1500; }
                case "Worker" -> { hourlyRate = 150; bonus = 1000; }
                default -> {
                    continue;
                }
            }

            double totalHours = getWorkingHours(empId, month, year);
            double basicSalary = totalHours * hourlyRate;
            double deduction = calculateDeduction(empId, month, year);
            double netSalary = basicSalary + bonus - deduction;

            ResultSet checkRs = SQLUtil.execute(
                    "SELECT 1 FROM Salary WHERE employee_id = ? AND pay_month = ? AND pay_year = ?",
                    empId, month, year
            );

            if (!checkRs.next()) {
                String sql = "INSERT INTO Salary (employee_id, basic_amount, bonus, deduction, net_amount, pay_month, pay_year)VALUES (?, ?, ?,?,?, ?, ?)";
                SQLUtil.execute(sql, empId, basicSalary, bonus,deduction, netSalary,month,year);


                atLeastOneGenerated = true;
            }
        }

        return atLeastOneGenerated;
    }

    @Override
    public boolean deleteAllSalary() throws SQLException, ClassNotFoundException {
        String sql ="DELETE FROM Salary";
        return SQLUtil.execute(sql);
    }
}
