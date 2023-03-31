package com.product.Myproduct.ServiceImpl;

import com.product.Myproduct.Entities.Product;
import com.product.Myproduct.Exceptions.ProductNotFoundException;
import com.product.Myproduct.Payload.ProductDto;
import com.product.Myproduct.Repository.ProductRepository;
import com.product.Myproduct.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = mapToProduct(productDto);
        Product newProduct = productRepository.save(product);
        ProductDto Dto = mapToProductDto(newProduct);
        return Dto;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
       return products.stream().map(product->mapToProductDto(product)).collect(Collectors.toList());

    }

    @Override
    public ProductDto getByProductById(long id) {
        Product products = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product", "id", id)
        );
       return mapToProductDto(products);
    }

    @Override
    public ProductDto updateProducts(ProductDto productDto, long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product", "id", id));
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        Product products = productRepository.save(product);
        return mapToProductDto(products);
    }

    @Override
    public void deleteByProductId(long id) {
        Product product = productRepository.findById(id).orElseThrow((
        ) -> new ProductNotFoundException("Products", "id", id));
        productRepository.delete(product);
    }

    private ProductDto mapToProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setProductName(product.getProductName());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

    private Product mapToProduct(ProductDto productDto){
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        return product;
    }
}
