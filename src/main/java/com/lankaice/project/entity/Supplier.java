package com.lankaice.project.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Supplier {
    private String supplierId;
    private String name;
    private String nic;
    private String contact;
    private String email;
    private String address;


}
