package com.isa.customerservice.customerAccountService.services;

import com.isa.customerservice.customerAccountService.dtos.CustomerAccountDto;

import java.util.Optional;

public interface CustomerAccountService {

    Optional<CustomerAccountDto> getProfileDetails(String email);
    String createCustomerProfile(CustomerAccountDto ownerAccountDto);
    String updateCustomerProfile(CustomerAccountDto customerAccountDto);
}
