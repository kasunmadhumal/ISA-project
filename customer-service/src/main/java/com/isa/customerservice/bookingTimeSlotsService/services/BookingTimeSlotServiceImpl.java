package com.isa.customerservice.bookingTimeSlotsService.services;

import com.isa.customerservice.bookingTimeSlotsService.dtos.AcceptedBookings;
import com.isa.customerservice.bookingTimeSlotsService.dtos.AvailableTimeSlot;
import com.isa.customerservice.bookingTimeSlotsService.dtos.BookedTimeSlotDetails;
import com.isa.customerservice.bookingTimeSlotsService.models.BookedServiceTimeSlot;
import com.isa.customerservice.bookingTimeSlotsService.repositories.IBookedTimeSlotRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingTimeSlotServiceImpl implements BookingTimeSlotService{

    private final IBookedTimeSlotRepository bookedTimeSlotRepository;
    private final KafkaCommunicationService kafkaCommunicationService;

    public BookingTimeSlotServiceImpl(IBookedTimeSlotRepository bookedTimeSlotRepository, KafkaCommunicationService kafkaCommunicationService){
        this.bookedTimeSlotRepository = bookedTimeSlotRepository;
        this.kafkaCommunicationService = kafkaCommunicationService;
    }
    @Override
    public Optional<List<BookedServiceTimeSlot>> bookedServiceTimeSlots(String userEmail){
        return Optional.ofNullable(bookedTimeSlotRepository.findByUserEmailAddress(userEmail));
    }

    @Override
    public Optional<String> bookServiceTimeSlot(BookedTimeSlotDetails bookedTimeSlotDetails){

        BookedServiceTimeSlot bookedServiceTimeSlot = BookedServiceTimeSlot.builder()
                .id(UUID.randomUUID().toString())
                .key(bookedTimeSlotDetails.getKey())
                .userEmailAddress(bookedTimeSlotDetails.getUserEmailAddress())
                .status(bookedTimeSlotDetails.getStatus())
                .acceptedStatus(bookedTimeSlotDetails.getAcceptedStatus())
                .numberOfVehiclesMaxAllowedForService(bookedTimeSlotDetails.getNumberOfVehiclesMaxAllowedForService())
                .availableBookingCountForService(bookedTimeSlotDetails.getAvailableBookingCountForService())
                .timeSlotAllocatedDate(bookedTimeSlotDetails.getTimeSlotAllocatedDate())
                .timeSlotAllocatedTime(bookedTimeSlotDetails.getTimeSlotAllocatedTime())
                .timeSlotAllocatedDuration(bookedTimeSlotDetails.getTimeSlotAllocatedDuration())
                .timeSlotAllocatedService(bookedTimeSlotDetails.getTimeSlotAllocatedService())
                .timeSlotAllocatedServiceDiscount(bookedTimeSlotDetails.getTimeSlotAllocatedServiceDiscount())
                .timeSlotAllocatedVehicles(bookedTimeSlotDetails.getTimeSlotAllocatedVehicles())
                .build();

        kafkaCommunicationService.sendBookedTimeSlot(bookedTimeSlotDetails);
        bookedTimeSlotRepository.save(bookedServiceTimeSlot);
        return Optional.of("successfully booked");

    }

    @Override
    public Optional<String> updateServiceTimeSlot(BookedTimeSlotDetails bookedTimeSlotDetails, String id){

        Optional<BookedServiceTimeSlot> bookedServiceTimeSlots = bookedTimeSlotRepository.findById(id);

        if (bookedServiceTimeSlots.isPresent()) {
            bookedServiceTimeSlots.get().setKey(bookedTimeSlotDetails.getKey());
            bookedServiceTimeSlots.get().setAcceptedStatus(bookedTimeSlotDetails.getAcceptedStatus());
            bookedServiceTimeSlots.get().setStatus(bookedTimeSlotDetails.getStatus());
            bookedServiceTimeSlots.get().setNumberOfVehiclesMaxAllowedForService(bookedTimeSlotDetails.getNumberOfVehiclesMaxAllowedForService());
            bookedServiceTimeSlots.get().setAvailableBookingCountForService(bookedTimeSlotDetails.getAvailableBookingCountForService());
            bookedServiceTimeSlots.get().setTimeSlotAllocatedDate(bookedTimeSlotDetails.getTimeSlotAllocatedDate());
            bookedServiceTimeSlots.get().setTimeSlotAllocatedTime(bookedTimeSlotDetails.getTimeSlotAllocatedTime());
            bookedServiceTimeSlots.get().setTimeSlotAllocatedDuration(bookedTimeSlotDetails.getTimeSlotAllocatedDuration());
            bookedServiceTimeSlots.get().setTimeSlotAllocatedService(bookedTimeSlotDetails.getTimeSlotAllocatedService());
            bookedServiceTimeSlots.get().setTimeSlotAllocatedServiceDiscount(bookedTimeSlotDetails.getTimeSlotAllocatedServiceDiscount());
            bookedServiceTimeSlots.get().setTimeSlotAllocatedVehicles(bookedTimeSlotDetails.getTimeSlotAllocatedVehicles());

            bookedTimeSlotRepository.save(bookedServiceTimeSlots.get());
        }

        return Optional.of("successfully updated");
    }


    @Override
    public Optional<String> deleteServiceTimeSlot(String id){
        bookedTimeSlotRepository.deleteById(id);
        return Optional.of("successfully deleted");
    }


    @Override
    public List<AvailableTimeSlot> getAvailableTimeSlots(){
        return kafkaCommunicationService.getAvailableTimeSlots();
    }

    @Override
    public List<AcceptedBookings> getAcceptedBookings(String userEmail){
        List<AcceptedBookings> acceptedBookings = kafkaCommunicationService.getAcceptedBookings();
        List<AcceptedBookings> myBookings = new ArrayList<>();
        for (AcceptedBookings acceptedBooking : acceptedBookings) {
            if (acceptedBooking.getUserEmailAddress().equals(userEmail)) {
                myBookings.add(acceptedBooking);
            }
        }
        return myBookings;
    }

}
