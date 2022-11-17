package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.Order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order getOrderById(long id);
    List<Order> getAllOrderByBooking_Id(long id);
    Order findTopByOrderByIdDesc();
}

