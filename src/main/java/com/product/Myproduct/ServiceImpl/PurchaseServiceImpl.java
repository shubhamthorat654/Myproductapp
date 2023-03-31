package com.product.Myproduct.ServiceImpl;

import com.product.Myproduct.Entities.Customer;
import com.product.Myproduct.Entities.Product;
import com.product.Myproduct.Entities.Purchase;
import com.product.Myproduct.Exceptions.ProductNotFoundException;
import com.product.Myproduct.Payload.PurchaseDto;
import com.product.Myproduct.Repository.CustomerRepository;
import com.product.Myproduct.Repository.ProductRepository;
import com.product.Myproduct.Repository.PurchaseRepository;
import com.product.Myproduct.Service.PurchaseService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseRepository purchaseRepository;
    private CustomerRepository customerRepository;

    private ProductRepository productRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.purchaseRepository = purchaseRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public PurchaseDto createPurchase(long customerId,PurchaseDto purchaseDto) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new ProductNotFoundException("Customer", "id", customerId)
        );

        Purchase purchase = mapToPurchase(purchaseDto);
        purchase.setCustomer(customer);
        Purchase newPurchase = purchaseRepository.save(purchase);
        PurchaseDto purchaseDto1 = mapToDto(newPurchase);
        return purchaseDto1;
    }

    @Override
    public List<PurchaseDto> getAll() {
        List<Purchase> all = purchaseRepository.findAll();
        return all.stream().map(orders -> mapToDto(orders)).collect(Collectors.toList());
    }

    @Override
    public PurchaseDto getOrderById(long id) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Order", "id", id)
        );
        return mapToDto(purchase);
    }

    @Override
    public PurchaseDto updateOrderById(long id, PurchaseDto purchaseDto) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("order", "id", id)
        );
        purchase.setQuantity(purchaseDto.getQuantity());
        purchase.setDate(purchaseDto.getDate());
        purchase.setPrice(purchaseDto.getPrice());
        Purchase save = purchaseRepository.save(purchase);
        return mapToDto(save);
    }

    @Override
    public void deleteOrderById(long id) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Order", "id", id)
        );
        purchaseRepository.delete(purchase);

    }

    @Override
    public List<Purchase> getPurchasesByCustomerId(long customerId) {
        List<Purchase> customId = purchaseRepository.findByCustomerId(customerId);
        return customId;
    }

    Purchase mapToPurchase(PurchaseDto purchaseDto){
        Purchase purchase = new Purchase();
        purchase.setQuantity(purchaseDto.getQuantity());
        purchase.setPrice(purchaseDto.getPrice());
        purchase.setDate(purchaseDto.getDate());
        purchase.setProduct(purchaseDto.getProduct());
        return  purchase;
    }

    PurchaseDto mapToDto(Purchase purchase){
        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setId(purchase.getId());
        purchaseDto.setDate(purchase.getDate());
        purchaseDto.setPrice(purchase.getPrice());
        purchaseDto.setQuantity(purchase.getQuantity());
        purchaseDto.setProduct(purchase.getProduct());
        return purchaseDto;
    }
}
