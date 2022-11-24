package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.Booking;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking getBookingById(long id);
    Booking findTopByOrderByIdDesc();

    @Query(value = "Select * from booking b where b.room_id = :roomId and status like :status", nativeQuery = true)
    List<Booking> getAllBookingsByRoomIdAndStatus(long roomId, String status);
}
