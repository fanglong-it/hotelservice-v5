package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.OrderPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPaymentRepository extends JpaRepository<OrderPayment, Long> {
    OrderPayment getOrderPaymentById(long id);
    OrderPayment findTopByOrderByIdDesc();

}

