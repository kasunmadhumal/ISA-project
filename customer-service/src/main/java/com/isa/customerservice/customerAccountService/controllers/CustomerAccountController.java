package com.isa.customerservice.customerAccountService.controllers;

import com.isa.customerservice.customerAccountService.dtos.CustomerAccountDto;
import com.isa.customerservice.customerAccountService.models.CustomerAccount;
import com.isa.customerservice.customerAccountService.services.CustomerAccountService;
import com.isa.customerservice.customerAccountService.services.CustomerAccountServiceImpl;
import com.isa.customerservice.customerAccountService.services.CustomerVehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customer-account/")
public class CustomerAccountController {


    private final CustomerAccountService customerAccountService;

    public CustomerAccountController(CustomerAccountService customerAccountService){
        this.customerAccountService = customerAccountService;
    }
    @PreAuthorize("hasRole('ROLE_NORMAL_USER')")
    @GetMapping("{email}")
    public ResponseEntity<Optional<CustomerAccount>> profileDetails(@PathVariable("email") String email){
        return ResponseEntity.ok(customerAccountService.getProfileDetails(email));
    }

    @PreAuthorize("hasRole('ROLE_NORMAL_USER')")
    @PostMapping
    public ResponseEntity<String> createProfile(@RequestBody CustomerAccountDto ownerAccountDto){
        return ResponseEntity.ok(customerAccountService.createCustomerProfile(ownerAccountDto));
    }

    @PreAuthorize("hasRole('ROLE_NORMAL_USER')")
    @PutMapping
    public ResponseEntity<String> updateProfile(@RequestBody CustomerAccountDto customerAccountDto){
        return ResponseEntity.ok(customerAccountService.updateCustomerProfile(customerAccountDto));
    }

}
