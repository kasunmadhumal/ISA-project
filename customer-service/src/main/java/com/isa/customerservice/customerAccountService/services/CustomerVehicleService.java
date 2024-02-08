package com.isa.customerservice.customerAccountService.services;

import com.isa.customerservice.customerAccountService.dtos.VehicleDto;
import com.isa.customerservice.customerAccountService.models.Vehicle;

import java.util.List;

public interface CustomerVehicleService {
    String addVehicle(VehicleDto vehicleDto);
    String updateVehicle(VehicleDto vehicleDto);
    List<Vehicle> getAllVehicles(String email);
}