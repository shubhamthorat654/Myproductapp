package com.product.Myproduct.Payload;

import lombok.Data;

@Data
public class ProductDto {
    private long id;
    private String productName;
    private double price;
}
