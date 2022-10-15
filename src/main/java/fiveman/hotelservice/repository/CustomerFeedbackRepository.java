package fiveman.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiveman.hotelservice.entities.CustomerFeedback;

@Repository
public interface CustomerFeedbackRepository extends JpaRepository<CustomerFeedback, Long>{
      CustomerFeedback getCustomerFeedbackById(long id);
}
