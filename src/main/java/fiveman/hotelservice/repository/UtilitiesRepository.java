package fiveman.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiveman.hotelservice.entities.Utilities;

@Repository
public interface UtilitiesRepository extends  JpaRepository<Utilities, Long>{
      Utilities getUtilitiesById(long id);
}
