package com.product.Myproduct.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException{

    private String productName;
    private String fieldName;
    private long value;

    public ProductNotFoundException(String productName, String fieldName, long value){
        super(String.format("%s not found with %s:'%s'",productName,fieldName,value));
        this.productName = productName;
        this.fieldName = fieldName;
        this.value=value;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
