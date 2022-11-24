package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.OrderPayment;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.OrderPaymentRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.OrderPaymentService;
import fiveman.hotelservice.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderPaymentServiceImpl implements OrderPaymentService {

    @Autowired
    OrderPaymentRepository orderPaymentRepository;

    
    
    // @Override
    // public OrderPayment getOrderPaymentByOrderId(long orderId) {
    //     OrderPayment orderPayment = orderPaymentRepository.getOrderPaymentByOrder_Id(orderId);
    //     if(orderPayment != null){
    //         return orderPayment;
    //     }
    //     throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Not exist by OrderId =" + orderId));
    // }

//    @Override
//    public Boolean existOrderPaymentByOrderId(long orderId) {
////        OrderPayment orderPayment = orderPaymentRepository.getOrderPaymentByOrder_Id(orderId);
////        if(orderPayment  != null){
////            // throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist id = " + orderPayment.getId()));
////            return true;
////        }
//        return false;
//    }

    // @Override
    // public Boolean existOrderPaymentByOrderId(long orderId) {
    //     OrderPayment orderPayment = orderPaymentRepository.getOrderPaymentByOrder_Id(orderId);
    //     if(orderPayment  != null){
    //         // throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist id = " + orderPayment.getId()));
    //         return true;
    //     }
    //     return false;
    // }


    @Override
    public OrderPayment getOrderPaymentById(long id) {
        if (!orderPaymentRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Not found id =" + id));
        }
        return orderPaymentRepository.getOrderPaymentById(id);
    }

    @Override
    public List<OrderPayment> getAllOrderPayment() {
        return orderPaymentRepository.findAll();
    }

    @Override
    public CustomResponseObject saveOrderPayment(OrderPayment billPayment) {
        if (orderPaymentRepository.existsById(billPayment.getId())) {
            throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist id = " + billPayment.getId()));
        }
        orderPaymentRepository.save(billPayment);
        return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding Success!");
    }

    @Override
    public CustomResponseObject updateOrderPayment(OrderPayment billPayment) {
        if (!orderPaymentRepository.existsById(billPayment.getId())) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL, "Not found id = " + billPayment.getId()));
        }
        orderPaymentRepository.save(billPayment);
        return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
    }

    @Override
    public CustomResponseObject deleteOrderPayment(long id) {
        if (!orderPaymentRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.DELETE_FAIL, "Not found id =" + id));
        }
        orderPaymentRepository.deleteById(id);
        return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
    }

}
