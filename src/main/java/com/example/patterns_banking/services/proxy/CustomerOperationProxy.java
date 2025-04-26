package com.example.patterns_banking.services.proxy;

import com.example.patterns_banking.dtos.CustomerDTO;
import com.example.patterns_banking.models.Customer;
import com.example.patterns_banking.repositories.ICustomerRepository;
import com.example.patterns_banking.services.CustomerService;
import org.springframework.stereotype.Component;

@Component
public class CustomerOperationProxy implements ICustomerOperation{
    private final ICustomerRepository customerRepository;
//    private final CustomerService customerService;

    public CustomerOperationProxy(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    //    this.customerService = customerService;
    }


    @Override
    public Boolean create(CustomerDTO customerDTO){
        validateEmail(customerDTO.getEmail());
        return true;
    }

    private void validateEmail(String email) {
        if (email.contains("yahoo")) {
            throw new IllegalArgumentException("Email :" + email+ " Es un email resringido para este sistema");
        }
    }
}
