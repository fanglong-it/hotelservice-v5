package fiveman.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiveman.hotelservice.entities.New;

@Repository
public interface NewRepository extends JpaRepository<New, Long>  {
    New getNewById(long id);
}