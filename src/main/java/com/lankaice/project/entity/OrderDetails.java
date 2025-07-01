package com.lankaice.project.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetails {
   private int orderId;
   private String productId;
   private int quantity;
   private double unitPrice;
   private double discount;
}
