package com.example.patterns_banking.services.proxy;

import com.example.patterns_banking.dtos.CustomerDTO;
import com.example.patterns_banking.models.Customer;
import com.example.patterns_banking.repositories.ICustomerRepository;
import com.example.patterns_banking.services.commands.*;
import org.springframework.stereotype.Component;

@Component
public class CustomerProxy implements ICustomerOperationsProxy {
  private final ICustomerRepository customerRepository;

  public CustomerProxy(ICustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public Customer createCustomer(CustomerDTO customerDTO) {
    validateEmail(customerDTO.getEmail());
    ICommand<Customer> command = new CreateCustomerCommand(customerRepository, customerDTO);
    return command.execute();
  }

  private void validateEmail(String email) {
    if (!email.contains("@")) {
      throw new IllegalArgumentException("No es un correo permitido "+ email);
    }
  }
}