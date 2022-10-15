package fiveman.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiveman.hotelservice.entities.RequestService;

@Repository
public interface RequestServiceRepository extends JpaRepository<RequestService , Long>{
      RequestService getRequestServiceById(long id);
}
