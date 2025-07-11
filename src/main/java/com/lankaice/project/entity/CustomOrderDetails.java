package com.lankaice.project.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomOrderDetails {
    private String productId;
    private int quantity;
    private double unitPrice;
    private double discount;
}
