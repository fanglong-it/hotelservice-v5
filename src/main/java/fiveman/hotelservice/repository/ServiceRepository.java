package fiveman.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiveman.hotelservice.entities.Service;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    Service getServiceById(Long id);
    List<Service> getAllByServiceCategory_Id(long service_category_id);



}
