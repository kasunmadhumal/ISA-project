package com.isa.customerservice.bookingTimeSlotsService.services;

import com.isa.customerservice.bookingTimeSlotsService.dtos.AcceptedBookings;
import com.isa.customerservice.bookingTimeSlotsService.dtos.AvailableTimeSlot;
import com.isa.customerservice.bookingTimeSlotsService.dtos.BookedTimeSlotDetails;

import java.util.List;

public interface KafkaCommunicationService {
    void sendBookedTimeSlot(BookedTimeSlotDetails bookedTimeSlotDetails);

    List<AvailableTimeSlot> getAvailableTimeSlots();

    List<AcceptedBookings> getAcceptedBookings();
}