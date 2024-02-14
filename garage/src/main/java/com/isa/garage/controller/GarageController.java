package com.isa.garage.controller;

import com.isa.garage.dto.AcceptedBookings;
import com.isa.garage.dto.AvailableTimeSlot;
import com.isa.garage.dto.BookedTimeSlotDetails;
import com.isa.garage.service.GarageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/garage")
public class GarageController {

    private final GarageService garageService;

    GarageController(GarageService garageService) {
        this.garageService = garageService;
    }


    @GetMapping("/booked-time-slots")
    public List<BookedTimeSlotDetails> bookedTimeSlots() {
        return garageService.bookedTimeSlotDetailsList();
    }

    @PostMapping("/accept-time-slot")
    public boolean acceptTimeSlot(@RequestBody AcceptedBookings acceptedBookings) {
        return garageService.acceptTimeSlot(acceptedBookings);
    }

    @PostMapping("/update-timeslot")
    public ResponseEntity<Map<String, String>> addServiceBookingTimeslot() throws InterruptedException {

        int range = 7;
        while (range > 0) {
            boolean result = garageService.addServiceBookingTimeslot(
                 AvailableTimeSlot.builder()
                            .key(UUID.randomUUID().toString())
                            .status("available")
                            .userEmailAddress("")
                            .timeSlotAllocatedDate("2021-12-12")
                            .timeSlotAllocatedTime("12:00")
                            .timeSlotAllocatedDuration("4 hour")
                            .timeSlotAllocatedService("car wash")
                            .numberOfVehiclesMaxAllowedForService(10)
                            .availableBookingCountForService(10)
                            .timeSlotAllocatedServiceDiscount(0.0)
                            .build()
            );
            System.out.println(result);
            Thread.sleep(10000);
            range--;
        }
        return new ResponseEntity<>(
                Map.of("message", "timeslot updated"),
                org.springframework.http.HttpStatus.OK
        );
    }

}
