package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.BillPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillPaymentRepository extends JpaRepository<BillPayment, Long> {
    BillPayment getBillPaymentById(long id);
}

