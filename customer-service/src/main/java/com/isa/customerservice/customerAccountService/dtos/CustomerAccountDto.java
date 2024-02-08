package com.isa.customerservice.customerAccountService.dtos;

import com.isa.customerservice.customerAccountService.models.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerAccountDto {

    private String firstName;
    private String lastName;
    private String email;
    private List<Vehicle> ownerVehicles;

}
