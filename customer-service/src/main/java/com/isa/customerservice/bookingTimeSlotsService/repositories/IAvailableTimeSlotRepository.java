package com.isa.customerservice.bookingTimeSlotsService.repositories;

import com.isa.customerservice.bookingTimeSlotsService.models.AvailableTimeSlots;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import static com.isa.customerservice.config.dbConfig.CollectionNames.AVAILABLE_TIME_SLOT_COLLECTION;

@Collection(AVAILABLE_TIME_SLOT_COLLECTION)
@Repository
public interface IAvailableTimeSlotRepository extends CouchbaseRepository<AvailableTimeSlots, String> {

    void deleteByKey(String key);
}
