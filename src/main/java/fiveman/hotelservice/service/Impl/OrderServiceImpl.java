package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.Order;
import fiveman.hotelservice.entities.OrderDetail;
import fiveman.hotelservice.entities.ServiceCategory;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.OrderDetailRepository;
import fiveman.hotelservice.repository.OrderRepository;
import fiveman.hotelservice.repository.ServiceRepository;
import fiveman.hotelservice.request.OrderDetailRequest;
import fiveman.hotelservice.request.OrderRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.OrderResponse;
import fiveman.hotelservice.service.OrderService;
import fiveman.hotelservice.utils.Common;
import fiveman.hotelservice.utils.Utilities;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    
    @Override
    public Order submitOrderService(OrderRequest orderRequest) {
        Order order = null;
        if(!orderRequest.getLOrderDetailRequests().isEmpty()){
            
            //Save order
            order = modelMapper.map(orderRequest, Order.class);
            order.setOrderPayment(null);
            order.setId(0);
            
            orderRepository.save(order);
            //Get Last OrderId
            order = orderRepository.findTopByOrderByIdDesc();

            //Save OrderDetailRequest
            double totalAmount = 0;
            for (OrderDetailRequest orderDetailRequest : orderRequest.getLOrderDetailRequests()) {
                orderDetailRequest.setId(0);
                orderDetailRequest.setOrder_Id(order.getId());
                OrderDetail orderDetail = modelMapper.map(orderDetailRequest, OrderDetail.class);
                double itemAmount = orderDetail.getQuantity() * orderDetail.getAmount();
                orderDetail.setAmount(itemAmount);
                orderDetailRepository.save(orderDetail);
                totalAmount += itemAmount;
                itemAmount = 0;
            }
            order.setTotalAmount(totalAmount);
            orderRepository.save(order);

        }else{
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Can't detect action !"));
        }    
        return orderRepository.getOrderById(order.getId());
    }

    @Override
    public List<Order> getAllOrderByBookingId(long id) {
        return orderRepository.getAllOrderByBooking_Id(id);
    }

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


    OrderResponse mapOrderToResponse(Order order){
        OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);
        return orderResponse;
    }

    @Autowired
    ServiceRepository serviceRepository;


    @Override
    public List<OrderResponse> getAllOrderFandB() {
        // List<Order> orders = orderRepository.getAllOrderByStatus("BOOKED");
        List<Order> orders = orderRepository.findAll();
        //BOOKED -> PROCESSING - > DONE
        List<OrderResponse> orderResponses = new ArrayList<>();

        // List<OrderResponse> tmpList = new ArrayList<>();
        for (Order order : orders) {
            List<OrderDetail> listOrderDetails = new ArrayList<>();
            OrderResponse orderResponse = mapOrderToResponse(order);
            orderResponse.getBooking().setHotel(null);
            orderResponse.getBooking().setOrders(null);
            orderResponse.getBooking().setRequestServices(null);
             for (OrderDetail orderDetail : orderResponse.getOrderDetails()) {
                fiveman.hotelservice.entities.Service service = orderDetail.getService();
                ServiceCategory serviceCategory = service.getServiceCategory();
                if(serviceCategory.isFoodAndBeverage()){
                    listOrderDetails.add(orderDetail);
                }
             }
            order.setOrderDetails(listOrderDetails);
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }


    

}
