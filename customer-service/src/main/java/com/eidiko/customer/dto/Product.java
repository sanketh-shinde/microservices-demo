package com.eidiko.customer.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    private String name;
    private double price;
    private String brand;
    private String category;

}
