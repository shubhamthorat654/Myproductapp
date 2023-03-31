package com.product.Myproduct.Service;

import com.product.Myproduct.Payload.CustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto createPost(CustomerDto customerDto);

    List<CustomerDto> getAllPosts();

    CustomerDto getById(long id);
}
