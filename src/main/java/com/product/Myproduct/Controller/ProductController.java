package com.product.Myproduct.Controller;

import com.product.Myproduct.Payload.ProductDto;
import com.product.Myproduct.ServiceImpl.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }
    @PostMapping
    public ResponseEntity<ProductDto> createProduct (@RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }

    @GetMapping
    List<ProductDto> getAllProducts (){
        List<ProductDto> allProducts = productService.getAllProducts();
        return allProducts;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getByProductId(@PathVariable(name = "id") long id){
        ProductDto byProductById = productService.getByProductById(id);
        return ResponseEntity.ok(byProductById);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,
        @PathVariable(name = "id")long id){
        ProductDto productDto1 = productService.updateProducts(productDto, id);
        return new ResponseEntity<>(productDto1,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id")long id){
        productService.deleteByProductId(id);
        return new ResponseEntity<>("Product has Deleted Successfully", HttpStatus.OK);
    }
}
