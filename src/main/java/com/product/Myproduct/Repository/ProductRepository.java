package com.product.Myproduct.Repository;

import com.product.Myproduct.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
