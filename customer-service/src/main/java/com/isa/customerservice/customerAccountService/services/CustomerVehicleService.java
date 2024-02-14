package com.isa.customerservice.customerAccountService.services;

import com.isa.customerservice.customerAccountService.dtos.VehicleDto;
import com.isa.customerservice.customerAccountService.models.Vehicle;

import java.util.List;
import java.util.Optional;

public interface CustomerVehicleService {
    String addVehicle(VehicleDto vehicleDto);
    String updateVehicle(VehicleDto vehicleDto);
    List<Vehicle> getAllVehicles(String email);

    Optional<String> deleteVehicle(String email, String vehicleNumber);
}