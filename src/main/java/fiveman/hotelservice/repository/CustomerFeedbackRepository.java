package fiveman.hotelservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fiveman.hotelservice.entities.CustomerFeedback;

@Repository
public interface CustomerFeedbackRepository extends JpaRepository<CustomerFeedback, Long> {
    CustomerFeedback getCustomerFeedbackById(long id);


    @Query(value = "SELECT * FROM hotelservice_v5.customer_feedback where booking_id = :booking_id", nativeQuery = true)
    List<CustomerFeedback> getCustomerFeedbackByBookingId(long booking_id);
}
