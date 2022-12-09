package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.Order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order getOrderById(long id);
    List<Order> getAllOrderByBooking_Id(long id);
    List<Order> getAllOrderByStatus(String status);

    @Query(value = "select ord.id, ord.create_by, ord.create_date, ord.last_modify_by, ord.total_amount, ord.update_date, ord.status, ord.booking_id, ord.order_payment_id from orders ord inner join order_detail orddetail on ord.id = orddetail.order_id where orddetail.service_id != 57 and orddetail.service_id != 58 and orddetail.service_id != 70 and orddetail.service_id != 71", nativeQuery = true)
    List<Order> getAllOrderWithServiceFoodAndBeverage();

    Order findTopByOrderByIdDesc();
}

