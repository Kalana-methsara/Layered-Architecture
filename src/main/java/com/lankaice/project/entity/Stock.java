package com.lankaice.project.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stock {
    private int stockId;
    private String productId;
    private String productName;
    private Integer qty;
    private String date;
    private String time;

}

