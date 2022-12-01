package fiveman.hotelservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fiveman.hotelservice.entities.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room getRoomById(long id);
    
    Room findTopByOrderByIdDesc();
    
    @Query("select r from Room r inner join  Booking b on r.id = b.room.id where b.departureDate = :departureDate and r.roomType.id = :roomTypeId")
    List<Room> getRoomByBookingEndDate(@Param("roomTypeId") long roomTypeId, @Param("departureDate") String departureDate);

    @Query(value = "SELECT r.id, r.create_by, r.description, r.last_modify_by, r.name, r.room_no, r.status,r.create_date ,r.update_date, r.hotel_id, r.room_type_id FROM booking b inner join room r on b.room_type_id = r.room_type_id where b.id = :booking_id and r.status = 0", nativeQuery = true)
    List<Room> getRoomAvaiByBookingId(long booking_id);


    @Query(value = "select r.id, r.create_by, r.create_date, r.description, r.last_modify_by, r.name, r.room_no, r.status, r.update_date, r.hotel_id, r.room_type_id from room r inner join booking b on r.id = b.room_id where b.status = 'CHECK IN' and (STR_TO_DATE(:today, '%d/%m/%Y %T') between STR_TO_DATE(b.actual_arrival_date, '%d/%m/%Y %T') and STR_TO_DATE(b.actual_departure_date, '%d/%m/%Y %T'));", nativeQuery = true)
    List<Room> getRoomCheckInToday(String today);
}
