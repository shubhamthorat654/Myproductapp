package com.product.Myproduct.ServiceImpl;

import com.product.Myproduct.Entities.Customer;
import com.product.Myproduct.Exceptions.ProductNotFoundException;
import com.product.Myproduct.Payload.CustomerDto;
import com.product.Myproduct.Repository.CustomerRepository;
import com.product.Myproduct.Service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto createPost(CustomerDto customerDto) {
        Customer customer = mapToEntity(customerDto);
        Customer newCustomer = customerRepository.save(customer);
        CustomerDto customerResponse = mapToDto(newCustomer);
        return customerResponse;
    }

    @Override
    public List<CustomerDto> getAllPosts() {
        List<Customer> customer = customerRepository.findAll();
        return customer.stream().map( customer1 -> mapToDto(customer1)).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getById(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Customer", "id", id)
        );
        return mapToDto(customer);
    }

    private CustomerDto mapToDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        return customerDto;
    }

    private Customer mapToEntity(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        return customer;
    }
}
