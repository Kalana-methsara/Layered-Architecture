package com.lankaice.project.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Customer {
    private String customerId;
    private String name;
    private String nic;
    private String email;
    private String contact;
    private String address;
    private String description;

}
