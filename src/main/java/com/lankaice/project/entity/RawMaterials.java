package com.lankaice.project.entity;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class RawMaterials{
    private int materialId;
    private String supplierId;
    private String name;
    private String unitType;
    private double unitCost;
    private String lastUpdate;
    private int quantityAvailable;


}