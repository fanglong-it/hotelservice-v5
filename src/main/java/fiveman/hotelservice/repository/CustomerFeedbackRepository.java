package fiveman.hotelservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fiveman.hotelservice.entities.CustomerFeedback;

@Repository
public interface CustomerFeedbackRepository extends JpaRepository<CustomerFeedback, Long> {
    CustomerFeedback getCustomerFeedbackById(long id);

    @Query(value = "SELECT * FROM hotelservice_v5.customer_feedback where booking_id = :booking_id", nativeQuery = true)
    List<CustomerFeedback> getCustomerFeedbackByBookingId(long booking_id);
    @Query(value = "select * " +
            "from customer_feedback " +
            "where STR_TO_DATE(customer_feedback.date_time, '%d/%m/%Y') between STR_TO_DATE(:startDate, '%d/%m/%Y') and STR_TO_DATE(:endDate, '%d/%m/%Y')", nativeQuery = true)
    List<CustomerFeedback> getCustomerFeedbackByBetween(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
