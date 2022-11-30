package fiveman.hotelservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fiveman.hotelservice.entities.RoomPrice;

@Repository
public interface RoomPriceRepository extends JpaRepository<RoomPrice, Long>{
      RoomPrice getRoomPriceById(long id);

      // @Query(value = "select rp.id, rp.date, rp.max_booking_room, rp.price, rp.room_type_id as room_type_id, rp.default_price from room_price rp inner join room_type rt on rp.room_type_id = rt.id where rp.date = :today and rp.room_type_id = :room_type_id;", nativeQuery = true)
      @Query(value = "select rp.id, rp.date, rp.max_booking_room, rp.price, rp.room_type_id, rp.default_price from room_price rp where rp.date = :today and rp.room_type_id = :roomType_Id", nativeQuery = true)
      RoomPrice getRoomPriceTodayByRoomType(String today, long roomType_Id);

}
