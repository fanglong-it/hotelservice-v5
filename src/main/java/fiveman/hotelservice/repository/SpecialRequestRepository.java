package fiveman.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiveman.hotelservice.entities.SpecialRequest;

@Repository
public interface SpecialRequestRepository extends JpaRepository<SpecialRequest, Long>{
      SpecialRequest getSpecialRequestById(long id);
}
