package fiveman.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiveman.hotelservice.entities.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room getRoomById(long id);
}
