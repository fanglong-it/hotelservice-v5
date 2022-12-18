package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    RoomType getRoomTypeById(Long id);

    @Query(value = "SELECT rt.id, rt.default_occupancy, rt.description, rt.is_active, rt.max_adult, rt.max_occupancy, rt.name, rt.max_children, rt.default_price, rt.bed_type, rt.code, rt.num_of_room, rt.max_booking_room from room_type rt inner join room r on rt.id = r.room_type_id where r.id = :room_id", nativeQuery = true)
    RoomType getRoomTypeByRoomId(long room_id);

    RoomType findTopByOrderByIdDesc();
    
}
