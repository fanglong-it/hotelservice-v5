package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.OrderPayment;
import fiveman.hotelservice.response.CustomResponseObject;

import java.util.List;

public interface OrderPaymentService {

    OrderPayment getOrderPaymentById(long id);

    // OrderPayment getOrderPaymentByOrderId(long orderId);

    // Boolean existOrderPaymentByOrderId(long orderId);

    List<OrderPayment> getAllOrderPayment();

    CustomResponseObject saveOrderPayment(OrderPayment billPayment);

    CustomResponseObject updateOrderPayment(OrderPayment billPayment);

    CustomResponseObject deleteOrderPayment(long id);

}
