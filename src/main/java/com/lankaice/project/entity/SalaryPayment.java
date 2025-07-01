package com.lankaice.project.entity;

import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalaryPayment {
    private int salaryPaymentId;
    private int salaryId;
    private Date paymentDate;
    private double salaryAmount;
    private String status;
}
