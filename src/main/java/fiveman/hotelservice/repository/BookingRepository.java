package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.Booking;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking getBookingById(long id);
    Booking findTopByOrderByIdDesc();
    List<Booking> getAllBookingsByRoomId(long roomId);
}
