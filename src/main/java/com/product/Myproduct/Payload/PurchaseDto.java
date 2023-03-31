package com.product.Myproduct.Payload;

import com.product.Myproduct.Entities.Product;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PurchaseDto {

    private  long id;
    private long customerId;
    private int product;
    private int price;
    private Integer quantity;
    private LocalDate date;

}
