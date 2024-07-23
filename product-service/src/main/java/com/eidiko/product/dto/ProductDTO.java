package com.eidiko.product.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO {

    private String name;
    private double  price;
    private String brand;
    private String category;

}
