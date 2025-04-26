package com.example.patterns_banking.services.proxy;

import com.example.patterns_banking.dtos.CustomerDTO;

public interface ICustomerOperation {
    Boolean create(CustomerDTO customerDTO);
}
