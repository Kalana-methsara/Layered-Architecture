package com.lankaice.project.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class InventoryCart {
    private int cartId;
    private String supplierId;
    private int materialId;
    private String name;
    private String unitType;
    private double unitPrice;
    private int quantity;
    private double total;

}
