package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.Order;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.OrderRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.OrderService;
import fiveman.hotelservice.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;


    @Override
    public List<Order> getAllBill() {
        return orderRepository.findAll();
    }

    @Override
    public Order getBillById(long id) {
        if (!orderRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Cant found id = " + id));
        }
        return orderRepository.getOrderById(id);
    }

    @Override
    public CustomResponseObject saveBill(Order bill) {
        if (orderRepository.existsById(bill.getId())) {
            throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist id = " + bill.getId()));
        }
        orderRepository.save(bill);
        return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding Success!");
    }

    @Override
    public CustomResponseObject updateBill(Order bill) {
        if (!orderRepository.existsById(bill.getId())) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL, "Not found id = " + bill.getId()));
        }
        orderRepository.save(bill);
        return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
    }

    @Override
    public CustomResponseObject deleteBillById(long id) {
        if (!orderRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.DELETE_FAIL, "Cant found id = " + id));
        }
        orderRepository.deleteById(id);
        return new CustomResponseObject(Common.DELETE_SUCCESS, "Update Success!");

    }
}
