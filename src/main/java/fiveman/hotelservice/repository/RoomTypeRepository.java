package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    RoomType getRoomTypeById(Long id);
}
