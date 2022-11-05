package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.OrderPayment;
import fiveman.hotelservice.response.CustomResponseObject;

import java.util.List;

public interface OrderPaymentService {
    OrderPayment getBillPaymentById(long id);

    List<OrderPayment> getAllBillPayment();

    CustomResponseObject saveBillPayment(OrderPayment billPayment);

    CustomResponseObject updateBillPayment(OrderPayment billPayment);

    CustomResponseObject deleteBillPayment(long id);

}
