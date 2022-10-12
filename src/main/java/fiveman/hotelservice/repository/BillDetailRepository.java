package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {
    BillDetail getBillDetailById(long id);

    List<BillDetail> getAllByBill_Id(long bill_Id);
}


