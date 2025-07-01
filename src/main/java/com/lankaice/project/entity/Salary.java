package com.lankaice.project.entity;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Salary {
    private int salaryId;
    private String employeeId;
    private String employeeName;
    private double basicSalary;
    private double bonus;
    private double deduction;
    private double netSalary;
    private int payMonth;
    private int payYear;
    private String status;

}
