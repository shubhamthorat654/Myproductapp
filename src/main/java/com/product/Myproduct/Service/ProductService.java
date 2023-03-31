package com.product.Myproduct.Service;

import com.product.Myproduct.Payload.ProductDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    public ProductDto getByProductById(long id);

    public ProductDto updateProducts(ProductDto productDto, long id);

    void deleteByProductId(long id);
}
