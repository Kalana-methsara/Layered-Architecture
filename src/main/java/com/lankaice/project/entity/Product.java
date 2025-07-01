package com.lankaice.project.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private String productId;
    private String name;
    private double weight;
    private double pricePerUnit;
}
