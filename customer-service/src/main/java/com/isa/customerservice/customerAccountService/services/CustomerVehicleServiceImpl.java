package com.isa.customerservice.customerAccountService.services;

import com.isa.customerservice.customerAccountService.dtos.VehicleDto;
import com.isa.customerservice.customerAccountService.models.CustomerAccount;
import com.isa.customerservice.customerAccountService.models.Vehicle;
import com.isa.customerservice.customerAccountService.repositories.ICustomerAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class CustomerVehicleServiceImpl implements CustomerVehicleService {

    private final ICustomerAccountRepository customerAccountRepository;

    public CustomerVehicleServiceImpl(ICustomerAccountRepository customerAccountRepository){
        this.customerAccountRepository = customerAccountRepository;
    }

    @Override
    public String addVehicle(VehicleDto vehicleDto){
        Optional<CustomerAccount> existOwnerAccount = customerAccountRepository.findByEmail(vehicleDto.getOwnerEmail());

        Vehicle newVehicle = Vehicle.builder()
                .model(vehicleDto.getModel())
                .vehicleNumber(vehicleDto.getVehicleNumber())
                .year(vehicleDto.getYear())
                .fuelType(vehicleDto.getFuelType())
                .vehicleType(vehicleDto.getVehicleType())
                .numberOfSeats(vehicleDto.getNumberOfSeats())
                .numberOfDoors(vehicleDto.getNumberOfDoors())
                .distanceLimit(vehicleDto.getDistanceLimit())
                .build();

        if(existOwnerAccount.isPresent()){
            List<Vehicle> existsVehicles = existOwnerAccount.get().getOwnerVehicles();
            existsVehicles.add(newVehicle);
            existOwnerAccount.get().setOwnerVehicles(existsVehicles);
            customerAccountRepository.save(existOwnerAccount.get());
        }
        return "successfully saved";
    }

    @Override
    public String updateVehicle(VehicleDto vehicleDto){
        Optional<CustomerAccount> existOwnerAccount = customerAccountRepository.findByEmail(vehicleDto.getOwnerEmail());
        if(existOwnerAccount.isPresent()){
            List<Vehicle> existVehicles = existOwnerAccount.get().getOwnerVehicles();
            for(Vehicle vehicle: existVehicles){
                if(Objects.equals(vehicle.getVehicleNumber(), vehicleDto.getVehicleNumber())){
                    existVehicles.remove(vehicle);
                    vehicle.setModel(vehicleDto.getModel());
                    vehicle.setVehicleNumber(vehicleDto.getVehicleNumber());
                    vehicle.setYear(vehicleDto.getYear());
                    vehicle.setFuelType(vehicle.getFuelType());
                    vehicle.setVehicleType(vehicleDto.getVehicleType());
                    vehicle.setNumberOfSeats(vehicleDto.getNumberOfSeats());
                    vehicle.setNumberOfDoors(vehicleDto.getNumberOfDoors());
                    vehicle.setDistanceLimit(vehicleDto.getDistanceLimit());
                    existVehicles.add(vehicle);
                    break;
                }
            }
            existOwnerAccount.get().setOwnerVehicles(existVehicles);
            customerAccountRepository.save(existOwnerAccount.get());
        }

        return "update successfully";
    }

    @Override
    public List<Vehicle> getAllVehicles(String email){
        Optional<CustomerAccount> customerAccount = customerAccountRepository.findByEmail(email);
        return customerAccount.map(CustomerAccount::getOwnerVehicles).orElse(null);
    }
}
