package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.Booking;

import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
        Booking getBookingById(long id);

        Booking findTopByOrderByIdDesc();

        @Query(value = "Select * from booking b where b.room_id = :roomId and status like :status", nativeQuery = true)
        List<Booking> getAllBookingsByRoomIdAndStatus(long roomId, String status);

        @Query(value = "select b.id, b.actual_arrival_date, b.actual_departure_date, b.arrival_date, b.confirmation_no, b.create_by, b.create_date, b.departure_date, b.last_modify_by, b.num_of_adult, b.num_of_children, b.room_payment, b.special_note, b.status, b.total_amount, b.update_date, b.customer_id, b.hotel_id, b.room_id, b.room_type_id from room r inner join booking b on r.id = b.room_id where b.status = 'CHECK IN' and (STR_TO_DATE(:today, '%d/%m/%Y %T') between STR_TO_DATE(b.actual_arrival_date, '%d/%m/%Y %T') and STR_TO_DATE(b.actual_departure_date, '%d/%m/%Y %T'));", nativeQuery = true)
        List<Booking> getBookingByCheckInAndRoomId(String today);

        @Query(value = "Select * from booking b where b.room_id = :roomId and b.status != 'CHECK OUT' and (STR_TO_DATE(:today, '%d/%m/%Y %T') between STR_TO_DATE(b.actual_arrival_date, '%d/%m/%Y %T') and STR_TO_DATE(b.actual_departure_date, '%d/%m/%Y %T'))", nativeQuery = true)
        Booking getBookingByRoomIdToday(long roomId, String today);

        @Query(value = "select count(b.id) from Booking b where STR_TO_DATE(b.createDate, '%d/%m/%Y') between STR_TO_DATE(:startDate, '%d/%m/%Y') and STR_TO_DATE(:endDate, '%d/%m/%Y')")
        String getBookedBetween(String startDate, String endDate);

        @Query(value = "select count(booking.id) from booking where booking.room_id > 0 and STR_TO_DATE(booking.actual_departure_date, '%d/%m/%Y') between STR_TO_DATE(:startDate, '%d/%m/%Y') and STR_TO_DATE(:endDate, '%d/%m/%Y')", nativeQuery = true)
        String getRoomBusyBetween(String startDate, String endDate);

        @Query(value = "select count(b.id) from Booking b where (b.status = 'CANCEL' or b.status = 'NOT SHOW') and STR_TO_DATE(b.createDate, '%d/%m/%Y') between STR_TO_DATE(:startDate, '%d/%m/%Y') and STR_TO_DATE(:endDate, '%d/%m/%Y')")
        String getCancelBetween(String startDate, String endDate);

        @Query(value = "select count(b.id) from Booking b where STR_TO_DATE(b.actualArrivalDate, '%d/%m/%Y') between STR_TO_DATE(:startDate, '%d/%m/%Y') and STR_TO_DATE(:endDate, '%d/%m/%Y')")
        String getActualArriveDayBetween(String startDate, String endDate);

        @Query(value = "select count(b.id) from Booking b where STR_TO_DATE(b.actualDepartureDate, '%d/%m/%Y') between STR_TO_DATE(:startDate, '%d/%m/%Y') and STR_TO_DATE(:endDate, '%d/%m/%Y') and b.status = 'CHECK OUT'")
        String getActualDepartureDay(String startDate, String endDate);

        @Query(value = "select count(c.id) from Booking b inner join CustomerBooking c on b.id = c.booking.id where STR_TO_DATE(b.actualArrivalDate, '%d/%m/%Y') between STR_TO_DATE(:startDate, '%d/%m/%Y') and STR_TO_DATE(:endDate, '%d/%m/%Y')")
        String getAllCustomerStay(String startDate, String endDate);

        @Query(value = "select sum(booking.total_amount) " +
                "from booking " +
                "where STR_TO_DATE(booking.actual_departure_date, '%d/%m/%Y') between STR_TO_DATE(:startDate, '%d/%m/%Y') and STR_TO_DATE(:endDate, '%d/%m/%Y') " +
                "and booking.status = 'CHECK OUT'", nativeQuery = true)
        String getRevenueInMonthByCurrentDate(String startDate, String endDate);

        @Query(value = "select sum(booking.total_amount) " +
                "from booking " +
                "where STR_TO_DATE(booking.actual_departure_date, '%d/%m/%Y') between STR_TO_DATE(:startDate, '%d/%m/%Y') and STR_TO_DATE(:endDate, '%d/%m/%Y') " +
                "and booking.status = 'CHECK OUT'", nativeQuery = true)
        String getRevenueCurrentDate(String startDate, String endDate);

        @Query(value = "select sum(b.id) from Booking b where STR_TO_DATE(b.actualDepartureDate, '%d/%m/%Y') between STR_TO_DATE(:startDate, '%d/%m/%Y') and STR_TO_DATE(:endDate, '%d/%m/%Y') and b.status = 'CANCEL'")
        String getCancelRevenueCurrentDate(String startDate, String endDate);

        @Query(value = "select sum(booking.total_amount) " +
                "from booking " +
                "where STR_TO_DATE(booking.create_date, '%d/%m/%Y') between STR_TO_DATE(:startDate, '%d/%m/%Y') and STR_TO_DATE(:endDate, '%d/%m/%Y') " +
                "and ( booking.status = 'CANCEL' or booking.status = 'NOT SHOW')", nativeQuery = true)
        String getCancelRevenueInMonthByCurrentDate(String startDate, String endDate);

//        @Query(value = "select *" +
//                        "from booking " +
//                        "where booking.status = 'CHECK OUT' and STR_TO_DATE(:today, '%d/%m/%Y') between " +
//                        "DATE_ADD(STR_TO_DATE(booking.actual_departure_date, '%d/%m/%Y'), INTERVAL -DAY(STR_TO_DATE(booking.actual_departure_date, '%d/%m/%Y'))+1 DAY) and "
//                        +
//                        "LAST_DAY(STR_TO_DATE(booking.actual_departure_date, '%d/%m/%Y'))", nativeQuery = true)
//        List<Booking> getRevenueEntireMonth(String startDate, String endDate);

        @Query(value = "select b.id, b.actual_arrival_date, b.actual_departure_date, b.arrival_date, b.confirmation_no, b.create_by, b.create_date, b.departure_date, b.last_modify_by, b.num_of_adult, b.num_of_children, b.room_payment, b.special_note, b.status, b.total_amount, b.update_date, b.customer_id, b.hotel_id, b.room_id, b.room_type_id from booking b where b.room_id = :room_id and b.status = 'CHECK IN'", nativeQuery = true)
        Booking getBookingByRoomId(long room_id);

        @Query(value = "select b.id, b.actual_arrival_date, b.actual_departure_date, b.arrival_date, b.confirmation_no, b.create_by, b.create_date, b.departure_date, b.last_modify_by, b.num_of_adult, b.num_of_children, b.room_payment, b.special_note, b.status, b.total_amount, b.update_date, b.customer_id, b.hotel_id, b.room_id, b.room_type_id from booking b inner join customer_stay_booking csb on b.id = csb.booking_id where csb.customer_id = :customer_id", nativeQuery = true)
        Booking getBookingByCustomerId(long customer_id);

        @Query(value = "select b.actualDepartureDate, sum(b.totalAmount) " +
                        "from Booking b " +
                        "where b.status = 'CHECK OUT' and STR_TO_DATE(SUBSTRING_INDEX(b.actualDepartureDate, ' ', 1), '%d/%m/%Y') between STR_TO_DATE(:dateStart, '%d/%m/%Y') and STR_TO_DATE(:dateEnd, '%d/%m/%Y')" +
                        "group by b.actualDepartureDate ")
        List<Tuple> getRevenueByBetweenDate(@Param(("dateStart")) String dateStart, @Param(("dateEnd")) String dateEnd);

        @Query(value = "select b.createDate, sum(b.totalAmount) " +
                "from Booking b " +
                "where ( b.status = 'CANCEL' or b.status = 'NOT SHOW' ) and STR_TO_DATE(SUBSTRING_INDEX(b.createDate, ' ', 1), '%d/%m/%Y') between STR_TO_DATE(:dateStart, '%d/%m/%Y') and STR_TO_DATE(:dateEnd, '%d/%m/%Y') " +
                "group by b.createDate ")
        List<Tuple> getRevenueCancelByBetweenDate(@Param(("dateStart")) String dateStart, @Param(("dateEnd")) String dateEnd);

}
