package fiveman.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiveman.hotelservice.entities.RoomTypeUtilities;

@Repository
public interface RoomTypeUtilitiesRepository extends JpaRepository<RoomTypeUtilities, Long>{
      RoomTypeUtilities getRoomTypeUtilitiesById(long id);
}
