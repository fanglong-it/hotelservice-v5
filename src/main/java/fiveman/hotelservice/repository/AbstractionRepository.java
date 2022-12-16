package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.Abstraction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AbstractionRepository extends JpaRepository<Abstraction, Long> {
    @Query(value = "SELECT * FROM abstraction a WHERE a.id = :id", nativeQuery = true)
    Abstraction getAbstractionById(long id);

    @Query(value = "SELECT * FROM abstraction", nativeQuery = true)
    List<Abstraction> findAbstractions();

    Abstraction findTopByOrderByIdDesc();
    
}
