package com.example.patterns_banking.services;

import com.example.patterns_banking.dtos.CustomerDTO;
import com.example.patterns_banking.models.Customer;
import com.example.patterns_banking.repositories.ICustomerRepository;
import com.example.patterns_banking.services.proxy.ICustomerOperation;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
  private final ICustomerRepository customerRepository;
  private final ICustomerOperation proxy;

  public CustomerService(ICustomerRepository customerRepository, ICustomerOperation proxy) {
    this.customerRepository = customerRepository;
    this.proxy = proxy;
  }

  public Customer create(CustomerDTO customerDTO) {

    proxy.create(customerDTO);

    Customer customer = Customer
            .builder()
            .name(customerDTO.getName())
            .email(customerDTO.getEmail())
            .build();

    // Implementar proxy para verificar que el correo no sea del dominio yahoo
    return customerRepository.save(customer);
  }

}