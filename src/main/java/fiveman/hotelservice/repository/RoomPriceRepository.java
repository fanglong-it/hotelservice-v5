package fiveman.hotelservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiveman.hotelservice.entities.RoomPrice;

@Repository
public interface RoomPriceRepository extends JpaRepository<RoomPrice, Long>{
      RoomPrice getRoomPriceById(long id);

      // List<RoomPrice> 
}
