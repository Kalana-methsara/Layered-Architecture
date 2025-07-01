package com.lankaice.project.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Billing{
    private int billingId;
    private int orderId;
    private String billingDate;
    private String paymentStatus;
    private double amount;
}
