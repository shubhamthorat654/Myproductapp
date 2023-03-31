package com.product.Myproduct.Controller;

import com.product.Myproduct.Entities.Purchase;
import com.product.Myproduct.Payload.PurchaseDto;
import com.product.Myproduct.ServiceImpl.PurchaseServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {

    private PurchaseServiceImpl purchaseService;

    public PurchaseController(PurchaseServiceImpl purchaseService) {
        this.purchaseService = purchaseService;
    }
    @PostMapping("/{customerId}/purchases")
    public ResponseEntity<PurchaseDto> createPurchase(@PathVariable (name = "customerId") long customerId,@RequestBody PurchaseDto purchaseDto){
        double discount = 0.0;
        int month = LocalDate.now().getMonthValue();
        if (month == 11) {//November
            discount= purchaseDto.getQuantity() *0.15;
        }if (purchaseDto.getQuantity() >= 20){
            discount = purchaseDto.getQuantity() *0.2;
        }
        else if (purchaseDto.getQuantity() >= 10) {
            discount = purchaseDto.getQuantity() *0.1;
        }
        double totalPrice = purchaseDto.getPrice() * purchaseDto.getQuantity() - discount;
        purchaseDto.setPrice((int) totalPrice);
        return new ResponseEntity<>(purchaseService.createPurchase(customerId,purchaseDto),HttpStatus.CREATED);
    }

    @GetMapping
    List<PurchaseDto> getAllPurchases(){
        List<PurchaseDto> all = purchaseService.getAll();
        return all;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDto> getOrderById(@PathVariable(name = "id") long id){
        PurchaseDto orderById = purchaseService.getOrderById(id);
        return ResponseEntity.ok(orderById);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseDto> updateOrderById(@RequestBody PurchaseDto purchaseDto,
                                                       @PathVariable(name = "id") long id){
        PurchaseDto purchaseDto1 = purchaseService.updateOrderById(id, purchaseDto);
        return new ResponseEntity<>(purchaseDto1,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable (name = "id") long id){
        purchaseService.deleteOrderById(id);
        return new ResponseEntity<>("Order deleted sucessfully",HttpStatus.OK);
    }

}
