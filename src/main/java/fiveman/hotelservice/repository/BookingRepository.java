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


    @Query(value = "Select * from booking b where b.room_id = :roomId and b.status != 'CHECK OUT' and (STR_TO_DATE(:today, '%d/%m/%Y %T') between STR_TO_DATE(b.actual_arrival_date, '%d/%m/%Y %T') and STR_TO_DATE(b.actual_departure_date, '%d/%m/%Y %T'))", nativeQuery = true)
    Booking getBookingByRoomIdToday(long roomId, String today);

    @Query(value = "select count(b.id) from Booking b where SUBSTRING_INDEX(b.createDate, ' ', 1) = :today and b.status = 'BOOKED'")
    long getBookedToday(String today);

    @Query(value = "select count(b.id) from Booking b where SUBSTRING_INDEX(b.createDate, ' ', 1) = :today and b.status = 'CHECK IN'")
    long getCheckInToday(String today);

    @Query(value = "select count(b.id) from Booking b where SUBSTRING_INDEX(b.createDate, ' ', 1) = :today and b.status = 'CANCEL'")
    long getCancelToday(String today);

    @Query(value = "select count(b.id) from Booking b where SUBSTRING_INDEX(b.actualArrivalDate, ' ', 1) = :today and b.status = 'BOOKED'")
    long getActualArriveDay(String today);

    @Query(value = "select count(b.id) from Booking b where SUBSTRING_INDEX(b.actualDepartureDate, ' ', 1) = :today and b.status = 'CHECK OUT'")
    long getActualDepartureDay(String today);

    @Query(value = "select count(c.id) from Booking b inner join CustomerBooking c on b.id = c.booking.id where b.status = 'CHECK IN'")
    long getAllCustomerStay();

    @Query(value = "select sum(booking.total_amount)" +
            "from booking " +
            "where STR_TO_DATE('29/11/2022', '%d/%m/%Y') between " +
            "DATE_ADD(STR_TO_DATE(booking.arrival_date, '%d/%m/%Y'), INTERVAL -DAY(STR_TO_DATE(booking.arrival_date, '%d/%m/%Y'))+1 DAY) and " +
            "LAST_DAY(STR_TO_DATE(booking.arrival_date, '%d/%m/%Y')) and booking.status = 'DONE' GROUP BY booking.total_amount" , nativeQuery = true)
    String getRevenueInMonthByCurrentDate(String today);

    @Query(value = "select sum(booking.total_amount)" +
            "from booking " +
            "where STR_TO_DATE('29/11/2022', '%d/%m/%Y') between " +
            "DATE_ADD(STR_TO_DATE(booking.arrival_date, '%d/%m/%Y'), INTERVAL -DAY(STR_TO_DATE(booking.arrival_date, '%d/%m/%Y'))+1 DAY) and " +
            "LAST_DAY(STR_TO_DATE(booking.arrival_date, '%d/%m/%Y')) and booking.status = 'CANCEL' GROUP BY booking.total_amount" , nativeQuery = true)
    String getCancelRevenueInMonthByCurrentDate(String today);
}
