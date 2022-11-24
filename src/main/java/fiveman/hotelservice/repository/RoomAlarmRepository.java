package fiveman.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiveman.hotelservice.entities.RoomAlarm;

@Repository
public interface RoomAlarmRepository extends JpaRepository<RoomAlarm, Long>{
      RoomAlarm getRoomAlarmById(long id);
      RoomAlarm findTopByOrderByIdDesc();
}
