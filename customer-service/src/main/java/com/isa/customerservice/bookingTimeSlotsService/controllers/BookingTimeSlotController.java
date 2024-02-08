package com.isa.customerservice.bookingTimeSlotsService.controllers;

import com.isa.customerservice.bookingTimeSlotsService.dtos.AcceptedBookings;
import com.isa.customerservice.bookingTimeSlotsService.dtos.AvailableTimeSlot;
import com.isa.customerservice.bookingTimeSlotsService.dtos.BookedTimeSlotDetails;
import com.isa.customerservice.bookingTimeSlotsService.models.BookedServiceTimeSlot;
import com.isa.customerservice.bookingTimeSlotsService.services.BookingTimeSlotService;
import com.isa.customerservice.bookingTimeSlotsService.services.BookingTimeSlotServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/time-slots/")
public class BookingTimeSlotController {

    private final BookingTimeSlotService bookingTimeSlotService;

    public BookingTimeSlotController(BookingTimeSlotService bookingTimeSlotService){
        this.bookingTimeSlotService = bookingTimeSlotService;
    }

    @PreAuthorize("hasRole('ROLE_NORMAL_USER')")
    @GetMapping("booked/{userEmail}")
    public Optional<List<BookedServiceTimeSlot>> bookedServiceTimeSlots(@PathVariable("userEmail") String userEmail){
        return ResponseEntity.ok(bookingTimeSlotService.bookedServiceTimeSlots(userEmail)).getBody();
    }

    @PreAuthorize("hasRole('ROLE_NORMAL_USER')")
    @PostMapping
    public Optional<String> bookServiceTimeSlot(@RequestBody BookedTimeSlotDetails bookedTimeSlotDetails){
        return ResponseEntity.ok(bookingTimeSlotService.bookServiceTimeSlot(bookedTimeSlotDetails)).getBody();
    }

    @PreAuthorize("hasRole('ROLE_NORMAL_USER')")
    @PutMapping("{id}")
    public Optional<String> updateServiceTimeSlot(@PathVariable("id") String id, @RequestBody BookedTimeSlotDetails bookedTimeSlotDetails){
        return ResponseEntity.ok(bookingTimeSlotService.updateServiceTimeSlot(bookedTimeSlotDetails, id)).getBody();
    }

    @PreAuthorize("hasRole('ROLE_NORMAL_USER')")
    @DeleteMapping("{id}")
    public String deleteServiceTimeSlot(@PathVariable("id") String id){
        return ResponseEntity.ok(bookingTimeSlotService.deleteServiceTimeSlot(id).orElse(null)).getBody();
    }

    @PreAuthorize("hasRole('ROLE_NORMAL_USER')")
    @GetMapping("available")
    public List<AvailableTimeSlot> availableTimeSlots(){
        return ResponseEntity.ok(bookingTimeSlotService.getAvailableTimeSlots()).getBody();
    }

    @PreAuthorize("hasRole('ROLE_NORMAL_USER')")
    @GetMapping("accepted/{userEmail}")
    public List<AcceptedBookings> acceptedBookings(@PathVariable("userEmail") String userEmail){
        return ResponseEntity.ok(bookingTimeSlotService.getAcceptedBookings(userEmail)).getBody();
    }

}
