package fiveman.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiveman.hotelservice.entities.OverviewService;

@Repository
public interface OverviewServiceRepository extends JpaRepository<OverviewService, Long>{
    OverviewService findOverviewServiceById(Long id);
}
