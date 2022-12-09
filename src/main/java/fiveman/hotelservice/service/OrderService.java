package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.Order;
import fiveman.hotelservice.request.OrderRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.OrderResponse;

import java.util.List;

public interface OrderService {
    Order getBillById(long id);

    List<Order> getAllBill();

    List<Order> getAllOrderFandB();

    List<Order> getAllOrderByBookingId(long id);
    
    Order submitOrderService(OrderRequest orderRequest);

    CustomResponseObject saveBill(Order bill);

    CustomResponseObject updateBill(Order bill);

    CustomResponseObject deleteBillById(long id);

    OrderResponse confirmOrderService(long orderId, String status);

    OrderResponse deleteOrderDetailService(long orderId, long orderDetailId);

}
