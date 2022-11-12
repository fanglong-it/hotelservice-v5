package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.Order;
import fiveman.hotelservice.response.CustomResponseObject;

import java.util.List;

public interface OrderService {
    Order getBillById(long id);

    List<Order> getAllBill();

    List<Order> getAllOrderByBookingId(long id);
    CustomResponseObject saveBill(Order bill);

    CustomResponseObject updateBill(Order bill);

    CustomResponseObject deleteBillById(long id);
}
