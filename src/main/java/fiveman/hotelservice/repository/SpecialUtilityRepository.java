package fiveman.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiveman.hotelservice.entities.SpecialUtility;

@Repository
public interface SpecialUtilityRepository extends JpaRepository<SpecialUtility, Long>{
      SpecialUtility getSpecialUtilityById(long id);
}
