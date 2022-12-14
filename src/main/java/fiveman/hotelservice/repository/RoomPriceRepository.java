package fiveman.hotelservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fiveman.hotelservice.entities.RoomPrice;

@Repository
public interface RoomPriceRepository extends JpaRepository<RoomPrice, Long>{
      RoomPrice getRoomPriceById(long id);

      @Query(value = "select rp.id, rp.date, rp.max_booking_room, rp.price, rp.room_type_id from room_price rp where rp.date = :today and rp.room_type_id = :roomType_Id", nativeQuery = true)
      RoomPrice getRoomPriceTodayByRoomType(String today, long roomType_Id);

}
