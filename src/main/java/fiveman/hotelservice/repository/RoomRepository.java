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
    
    @Query("select r from Room r inner join Booking b on r.id = b.room.id where b.departureDate = :departureDate and r.roomType.id = :roomTypeId")
    List<Room> getRoomByBookingEndDate(@Param("roomTypeId") long roomTypeId, @Param("departureDate") String departureDate);
}
