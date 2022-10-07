package fiveman.hotelservice.repository;


import fiveman.hotelservice.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository  extends JpaRepository<Hotel, Long> {
    Hotel getHotelById(Long id);
}


