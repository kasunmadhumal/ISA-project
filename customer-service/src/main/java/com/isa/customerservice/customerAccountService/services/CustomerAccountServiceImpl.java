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
    public Optional<CustomerAccountDto> getProfileDetails(String email){
        Optional<CustomerAccount> customerAccount = customerAccountRepository.findByEmail(email);
        if(customerAccount.isEmpty()){
            return Optional.empty();
        }
        CustomerAccountDto customerAccountDto = CustomerAccountDto.builder()
                .firstName(customerAccount.get().getFirstName())
                .lastName(customerAccount.get().getLastName())
                .email(customerAccount.get().getEmail())
                .address(customerAccount.get().getAddress())
                .phoneNumber(customerAccount.get().getPhoneNumber())
                .age(customerAccount.get().getAge())
                .gender(customerAccount.get().getGender())
                .profilePicture(customerAccount.get().getProfilePicture())
                .ownerVehicles(customerAccount.get().getOwnerVehicles())
                .build();

        return Optional.of(customerAccountDto);
    }

    @Override
    public String createCustomerProfile(CustomerAccountDto ownerAccountDto){

        if(getProfileDetails(ownerAccountDto.getEmail()).isPresent()){
            return "profile already exists";
        }

        CustomerAccount customerAccount = CustomerAccount.builder()
                .id(UUID.randomUUID().toString())
                .firstName(ownerAccountDto.getFirstName())
                .lastName(ownerAccountDto.getLastName())
                .email(ownerAccountDto.getEmail())
                .address(ownerAccountDto.getAddress())
                .age(ownerAccountDto.getAge())
                .phoneNumber(ownerAccountDto.getPhoneNumber())
                .gender(ownerAccountDto.getGender())
                .profilePicture(ownerAccountDto.getProfilePicture())
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
            ownerAccountExist.get().setAddress(customerAccountDto.getAddress());
            ownerAccountExist.get().setAge(customerAccountDto.getAge());
            ownerAccountExist.get().setPhoneNumber(customerAccountDto.getPhoneNumber());
            ownerAccountExist.get().setGender(customerAccountDto.getGender());
            ownerAccountExist.get().setProfilePicture(customerAccountDto.getProfilePicture());
            ownerAccountExist.get().setOwnerVehicles(customerAccountDto.getOwnerVehicles());

            customerAccountRepository.save(ownerAccountExist.get());
        }

        return "successfully updated";
    }

}
