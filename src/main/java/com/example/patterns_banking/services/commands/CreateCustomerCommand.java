package com.example.patterns_banking.services.commands;

import com.example.patterns_banking.dtos.CustomerDTO;
import com.example.patterns_banking.models.Customer;
import com.example.patterns_banking.repositories.ICustomerRepository;

public class CreateCustomerCommand implements ICommand<Customer> {
  private final ICustomerRepository customerRepository;
  private final CustomerDTO customerDTO;

  public CreateCustomerCommand(ICustomerRepository customerRepository, CustomerDTO customerDTO) {
    this.customerRepository = customerRepository;
    this.customerDTO = customerDTO;
  }

  @Override
  public Customer execute() {

    Customer customer = Customer
            .builder()
            .name(customerDTO.getName())
            .email(customerDTO.getEmail())
            .build();

    return customerRepository.save(customer);
  }
}