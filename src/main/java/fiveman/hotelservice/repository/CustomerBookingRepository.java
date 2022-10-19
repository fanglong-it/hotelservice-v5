package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.CustomerBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerBookingRepository extends JpaRepository<CustomerBooking, Long> {
    List<CustomerBooking> getAllByBooking_Id(long id);
}
