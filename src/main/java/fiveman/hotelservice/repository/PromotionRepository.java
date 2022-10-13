package fiveman.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiveman.hotelservice.entities.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long>{
	Promotion getPromotionById(long id);
}
