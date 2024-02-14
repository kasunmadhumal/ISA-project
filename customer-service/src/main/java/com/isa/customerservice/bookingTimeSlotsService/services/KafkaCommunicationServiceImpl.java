package com.isa.customerservice.bookingTimeSlotsService.services;

import com.couchbase.client.core.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.customerservice.bookingTimeSlotsService.dtos.AcceptedBookings;
import com.isa.customerservice.bookingTimeSlotsService.dtos.AvailableTimeSlot;
import com.isa.customerservice.bookingTimeSlotsService.dtos.BookedTimeSlotDetails;
import com.isa.customerservice.bookingTimeSlotsService.models.AcceptedBookingTimeslot;
import com.isa.customerservice.bookingTimeSlotsService.models.AvailableTimeSlots;
import com.isa.customerservice.bookingTimeSlotsService.repositories.IAcceptedBookingTimeSlotRepository;
import com.isa.customerservice.bookingTimeSlotsService.repositories.IAvailableTimeSlotRepository;
import com.isa.customerservice.bookingTimeSlotsService.repositories.IBookedTimeSlotRepository;
import com.isa.customerservice.config.kafkaConfig.constant.TopicsNames;
import lombok.Getter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Service
public class KafkaCommunicationServiceImpl implements KafkaCommunicationService {


    private final List<AvailableTimeSlot> availableTimeSlots = new ArrayList<>();

    private final List<AcceptedBookings> acceptedBookings = new ArrayList<>();

    private final KafkaTemplate<String, BookedTimeSlotDetails> kafkaTemplateForBookedTimeSlot;

    private final IAcceptedBookingTimeSlotRepository acceptedBookingTimeSlotRepository;

    private final IBookedTimeSlotRepository bookedTimeSlotRepository;

    private final IAvailableTimeSlotRepository availableTimeSlotRepository;


    public KafkaCommunicationServiceImpl(KafkaTemplate<String, BookedTimeSlotDetails> kafkaTemplateForBookedTimeSlot, IAcceptedBookingTimeSlotRepository acceptedBookingTimeSlotRepository, IBookedTimeSlotRepository bookedTimeSlotRepository, IAvailableTimeSlotRepository availableTimeSlotRepository) {
        this.kafkaTemplateForBookedTimeSlot = kafkaTemplateForBookedTimeSlot;
        this.acceptedBookingTimeSlotRepository = acceptedBookingTimeSlotRepository;
        this.bookedTimeSlotRepository = bookedTimeSlotRepository;
        this.availableTimeSlotRepository = availableTimeSlotRepository;
    }

    @KafkaListener(topics = TopicsNames.AVAILABLE_TIME_SLOTS, groupId = "user-group")
    public void availableTimeSlots(List<AvailableTimeSlot> availableTimeSlot) {
        try {
            if (!availableTimeSlot.isEmpty()) {
                String object = availableTimeSlot.toString();
                String json = object.substring(1, object.length() - 1);
                ObjectMapper objectMapper = new ObjectMapper();
                AvailableTimeSlot resultObject = objectMapper.readValue(json, AvailableTimeSlot.class);
                if (resultObject.getStatus().equals("available")) {
                    availableTimeSlots.add(resultObject);
                    AvailableTimeSlots availableTimeSlotsObject = AvailableTimeSlots.builder()
                            .id(UUID.randomUUID().toString())
                            .key(resultObject.getKey())
                            .status(resultObject.getStatus())
                            .userEmailAddress(resultObject.getUserEmailAddress())
                            .timeSlotAllocatedDate(resultObject.getTimeSlotAllocatedDate())
                            .timeSlotAllocatedTime(resultObject.getTimeSlotAllocatedTime())
                            .timeSlotAllocatedDuration(resultObject.getTimeSlotAllocatedDuration())
                            .timeSlotAllocatedService(resultObject.getTimeSlotAllocatedService())
                            .numberOfVehiclesMaxAllowedForService(resultObject.getNumberOfVehiclesMaxAllowedForService())
                            .availableBookingCountForService(resultObject.getAvailableBookingCountForService())
                            .timeSlotAllocatedServiceDiscount(resultObject.getTimeSlotAllocatedServiceDiscount())
                            .build();
                    availableTimeSlotRepository.save(availableTimeSlotsObject);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @KafkaListener(topics = TopicsNames.ACCEPTED_BOOKING, groupId = "user-group")
    public void acceptedTimeSlots(List<AcceptedBookings> acceptedBookings) {
        try {
            if (!acceptedBookings.isEmpty()) {
                System.out.println(acceptedBookings);
                String object = acceptedBookings.toString();
                String json = object.substring(1, object.length() - 1);
                ObjectMapper objectMapper = new ObjectMapper();
                AcceptedBookings resultObject = objectMapper.readValue(json, AcceptedBookings.class);
                this.acceptedBookings.add(resultObject);
                bookedTimeSlotRepository.deleteByKey(resultObject.getKey());

                AcceptedBookingTimeslot acceptedBookingTimeslot = AcceptedBookingTimeslot.builder().id(UUID.randomUUID().toString()).key(resultObject.getKey()).status(resultObject.getStatus()).acceptedStatus(resultObject.getAcceptedStatus()).userEmailAddress(resultObject.getUserEmailAddress()).timeSlotAllocatedDate(resultObject.getTimeSlotAllocatedDate()).timeSlotAllocatedTime(resultObject.getTimeSlotAllocatedTime()).timeSlotAllocatedDuration(resultObject.getTimeSlotAllocatedDuration()).timeSlotAllocatedService(resultObject.getTimeSlotAllocatedService()).numberOfVehiclesMaxAllowedForService(resultObject.getNumberOfVehiclesMaxAllowedForService()).availableBookingCountForService(resultObject.getAvailableBookingCountForService()).timeSlotAllocatedServiceDiscount(resultObject.getTimeSlotAllocatedServiceDiscount()).timeSlotAllocatedVehicles(resultObject.getTimeSlotAllocatedVehicles()).build();

                acceptedBookingTimeSlotRepository.save(acceptedBookingTimeslot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void sendBookedTimeSlot(BookedTimeSlotDetails bookedTimeSlotDetails) {
        kafkaTemplateForBookedTimeSlot.send(TopicsNames.BOOKED_TIME_SLOTS, bookedTimeSlotDetails);
    }


}
