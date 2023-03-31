package com.product.Myproduct.Repository;

import com.product.Myproduct.Entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {

        List<Purchase> findByCustomerId(long customerId);
        List<Purchase> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
