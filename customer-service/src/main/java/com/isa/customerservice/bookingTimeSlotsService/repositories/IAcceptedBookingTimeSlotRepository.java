package com.isa.customerservice.bookingTimeSlotsService.repositories;


import com.isa.customerservice.bookingTimeSlotsService.models.AcceptedBookingTimeslot;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import static com.isa.customerservice.config.dbConfig.CollectionNames.ACCEPTED_BOOKING_COLLECTION;

@Collection(ACCEPTED_BOOKING_COLLECTION)
@Repository
public interface IAcceptedBookingTimeSlotRepository extends CouchbaseRepository<AcceptedBookingTimeslot, String> {
}
