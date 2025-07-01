package com.lankaice.project.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderPayment {
    private String paymentId;
    private int orderId;
    private String paymentMethod;
    private int itemCount;
    private double subtotal;
    private double discount;
    private double netTotal;
    private String paymentDate;
    private String status;


}
