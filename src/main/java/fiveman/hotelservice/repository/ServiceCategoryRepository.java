package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Long> {
    ServiceCategory getServiceCategoryById(Long id);
}
