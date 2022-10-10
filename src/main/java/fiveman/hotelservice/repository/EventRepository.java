package fiveman.hotelservice.repository;


import fiveman.hotelservice.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event getEventById(Long id);
}
