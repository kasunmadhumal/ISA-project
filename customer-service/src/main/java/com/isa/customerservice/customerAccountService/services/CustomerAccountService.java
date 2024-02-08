package com.isa.customerservice.customerAccountService.services;

import com.isa.customerservice.customerAccountService.dtos.CustomerAccountDto;
import com.isa.customerservice.customerAccountService.models.CustomerAccount;

import java.util.Optional;

public interface CustomerAccountService {

    Optional<CustomerAccount> getProfileDetails(String email);
    String createCustomerProfile(CustomerAccountDto ownerAccountDto);
    String updateCustomerProfile(CustomerAccountDto customerAccountDto);
}
