package com.lankaice.project.entity;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomOrder {
    private String orderId;
    private String customerId;
    private String orderDate;
    private String orderTime;
    private double totalAmount;
    private String name;
    private String nic;
    private String email;
    private String contact;

    public List<CustomOrderDetails> orderDetailsList;
}
