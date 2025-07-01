package com.lankaice.project.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    private String employeeId;
    private String name;
    private String nic;
    private String contact;
    private String email;
    private String jobRole;
    private String address;
    private String joinDate;
    private String dateOfBirth;
    private String gender;
    private String bankAccountNo;
    private String bankBranch;
    private String licenseNumber;
}
