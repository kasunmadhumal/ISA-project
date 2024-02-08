package com.isa.customerservice.customerAccountService.controllers;


import com.isa.customerservice.customerAccountService.dtos.VehicleDto;
import com.isa.customerservice.customerAccountService.models.Vehicle;
import com.isa.customerservice.customerAccountService.services.CustomerVehicleService;
import com.isa.customerservice.customerAccountService.services.CustomerVehicleServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer-vehicle/")
public class CustomerVehicleController {


    private final CustomerVehicleService customerVehicleService;

    public CustomerVehicleController(CustomerVehicleService customerVehicleService){
        this.customerVehicleService = customerVehicleService;
    }
    @PreAuthorize("hasRole('ROLE_NORMAL_USER')")
    @PostMapping
    public ResponseEntity<String> addVehicle(@RequestBody VehicleDto vehicleDto){
         return ResponseEntity.ok(customerVehicleService.addVehicle(vehicleDto));
    }

    @PreAuthorize("hasRole('ROLE_NORMAL_USER')")
    @PutMapping
    public ResponseEntity<String> updateVehicle(@RequestBody VehicleDto vehicleDto){
        return ResponseEntity.ok(customerVehicleService.updateVehicle(vehicleDto));
    }

    @PreAuthorize("hasRole('ROLE_NORMAL_USER')")
    @GetMapping("all-vehicles/{email}")
    public ResponseEntity<List<Vehicle>> getAllVehicles(@PathVariable("email") String email){
        return ResponseEntity.ok(customerVehicleService.getAllVehicles(email));
    }

}
