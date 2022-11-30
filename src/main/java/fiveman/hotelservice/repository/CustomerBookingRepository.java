package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.CustomerBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerBookingRepository extends JpaRepository<CustomerBooking, Long> {
    List<CustomerBooking> getAllByBooking_Id(long id);


    @Query(value = "select csb.id, csb.primary_customer, csb.booking_id, csb.customer_id from booking b inner join customer_stay_booking csb on b.id = csb.booking_id where booking_id = :booking_id order by id limit 1;", nativeQuery = true)
    CustomerBooking selectPrimaryCustomerByBooking(long booking_id);

}
