package com.isa.customerservice.customerAccountService.services;

import com.isa.customerservice.customerAccountService.dtos.CustomerAccountDto;
import com.isa.customerservice.customerAccountService.models.CustomerAccount;
import com.isa.customerservice.customerAccountService.repositories.ICustomerAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService{

    private ICustomerAccountRepository customerAccountRepository;

    public CustomerAccountServiceImpl(ICustomerAccountRepository customerAccountRepository){
        this.customerAccountRepository = customerAccountRepository;
    }

    @Override
    public Optional<CustomerAccount> getProfileDetails(String email){
        return customerAccountRepository.findByEmail(email);
    }

    @Override
    public String createCustomerProfile(CustomerAccountDto ownerAccountDto){
        CustomerAccount customerAccount = CustomerAccount.builder()
                .id(UUID.randomUUID().toString())
                .firstName(ownerAccountDto.getFirstName())
                .lastName(ownerAccountDto.getLastName())
                .email(ownerAccountDto.getEmail())
                .ownerVehicles(ownerAccountDto.getOwnerVehicles())
                .build();
        customerAccountRepository.save(customerAccount);
        return "successfully created";
    }

    @Override
    public String updateCustomerProfile(CustomerAccountDto customerAccountDto){

        Optional<CustomerAccount> ownerAccountExist = customerAccountRepository.findByEmail(customerAccountDto.getEmail());

        if (ownerAccountExist.isPresent()) {

            ownerAccountExist.get().setFirstName(customerAccountDto.getFirstName());
            ownerAccountExist.get().setLastName(customerAccountDto.getLastName());
            ownerAccountExist.get().setEmail(customerAccountDto.getEmail());
            ownerAccountExist.get().setOwnerVehicles(customerAccountDto.getOwnerVehicles());

            customerAccountRepository.save(ownerAccountExist.get());
        }

        return "successfully updated";
    }

}
