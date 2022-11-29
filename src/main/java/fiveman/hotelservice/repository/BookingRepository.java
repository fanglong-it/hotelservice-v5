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


    @Query(value = "Select * from booking b where b.room_id = :roomId and b.status != 'CHECK OUT' and (STR_TO_DATE(:today, '%d/%m/%Y %T') between STR_TO_DATE(b.actual_arrival_date, '%d/%m/%Y %T') and STR_TO_DATE(b.actual_departure_date, '%d/%m/%Y %T'));", nativeQuery = true)
    Booking getBookingByRoomIdToday(long roomId, String today);

}
