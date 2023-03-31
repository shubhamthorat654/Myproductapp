package com.product.Myproduct.Controller;

import com.product.Myproduct.Payload.CustomerDto;
import com.product.Myproduct.ServiceImpl.CustomerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CustomerController {

    private CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createPost(@RequestBody CustomerDto customerDto ){
        ResponseEntity<CustomerDto> customerDtoResponseEntity = new ResponseEntity<>(customerService.createPost(customerDto), HttpStatus.CREATED);
        return customerDtoResponseEntity;
    }

    @GetMapping
    public List<CustomerDto> getAllPost(){
        return customerService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable(name = "id") long id){
        CustomerDto byId = customerService.getById(id);
        return ResponseEntity.ok(byId);
    }

}
