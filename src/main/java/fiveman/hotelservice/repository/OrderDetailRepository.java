package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    OrderDetail getOrderDetailById(long id);

    List<OrderDetail> getAllByOrder_Id(long order_id);
    }


