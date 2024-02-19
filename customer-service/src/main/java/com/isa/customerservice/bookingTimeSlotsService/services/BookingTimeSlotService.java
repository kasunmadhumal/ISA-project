package com.isa.customerservice.bookingTimeSlotsService.services;

import com.isa.customerservice.bookingTimeSlotsService.dtos.AcceptedBookings;
import com.isa.customerservice.bookingTimeSlotsService.dtos.AvailableTimeSlot;
import com.isa.customerservice.bookingTimeSlotsService.dtos.BookedTimeSlotDetails;
import com.isa.customerservice.bookingTimeSlotsService.models.BookedServiceTimeSlot;

import java.util.List;
import java.util.Optional;

public interface BookingTimeSlotService {
    Optional<List<BookedServiceTimeSlot>> bookedServiceTimeSlots(String userEmail);
    Optional<String> bookServiceTimeSlot(BookedTimeSlotDetails bookedTimeSlotDetails);
    Optional<String> updateServiceTimeSlot(BookedTimeSlotDetails bookedTimeSlotDetails, String id);
    Optional<String> deleteServiceTimeSlot(String id);
    List<AvailableTimeSlot> getAvailableTimeSlots();
    List<AcceptedBookings> getAcceptedBookings(String userEmail);
}
