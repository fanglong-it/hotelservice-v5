package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    PaymentMethod getPaymentMethodById(long id);
}

