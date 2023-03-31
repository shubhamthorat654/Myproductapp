package com.product.Myproduct.Service;

import com.product.Myproduct.Entities.Purchase;
import com.product.Myproduct.Payload.PurchaseDto;

import java.math.BigDecimal;
import java.util.List;

public interface PurchaseService {

    public PurchaseDto createPurchase(long customerId,PurchaseDto purchaseDto);

    List<PurchaseDto> getAll();

    PurchaseDto getOrderById(long id);

    PurchaseDto updateOrderById(long id, PurchaseDto purchaseDto);

    void deleteOrderById(long id);

    List<Purchase> getPurchasesByCustomerId(long customerId);
}
