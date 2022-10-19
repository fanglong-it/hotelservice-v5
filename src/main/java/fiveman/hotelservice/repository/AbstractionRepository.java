package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.Abstraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbstractionRepository extends JpaRepository<Abstraction, Long> {
    Abstraction getAbstractionById(long id);
}
