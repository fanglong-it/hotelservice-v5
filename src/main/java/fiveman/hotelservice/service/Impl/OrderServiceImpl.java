package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.Order;
import fiveman.hotelservice.entities.OrderDetail;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.OrderDetailRepository;
import fiveman.hotelservice.repository.OrderRepository;
import fiveman.hotelservice.request.OrderDetailRequest;
import fiveman.hotelservice.request.OrderRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.OrderService;
import fiveman.hotelservice.utils.Common;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    // public class OrderDetailRequest {

    //     @ApiModelProperty(required = true)
    //     private long id;
        
    //     @ApiModelProperty(required = true)
    //     private long service_Id;
    
    //     @ApiModelProperty(required = true)
    //     private long order_Id;
    
    //     @ApiModelProperty(required = true)
    //     private int quantity;
    
    //     @ApiModelProperty(required = true)
    //     private double price;
    
    //     @ApiModelProperty(required = true)
    //     private double amount;
    
    //     private String orderDate;
    
    // }

    // OrderDetailResponse mapBillDetailToResponse(OrderDetail billDetail) {
    //     //        private long id;
    //     //        private long service_Id;
    //     //        private long bill_Id;
    //     //        private int quantity;
    //     //        private double price;
    //     //        private double amount;
    //     //        private int status;
    //             OrderDetailResponse billDetailResponse
    //                     = new OrderDetailResponse();
    //             billDetailResponse.setId(billDetail.getId());
    //             billDetailResponse.setService(serviceRepository.getServiceById(billDetail.getService().getId()));
    //             billDetailResponse.setOrder_Id(billDetail.getOrder().getId());
    //             billDetailResponse.setQuantity(billDetail.getQuantity());
    //             billDetailResponse.setPrice(billDetail.getPrice());
    //             billDetailResponse.setAmount(billDetail.getAmount());
    //             billDetailResponse.setOrderDate(billDetail.getOrderDate());
    //             return billDetailResponse;
    //         }

    
    @Override
    public Order submitOrderService(OrderRequest orderRequest) {
        Order order = null;
        if(!orderRequest.getLOrderDetailRequests().isEmpty()){
            
            //Savev order
            order = modelMapper.map(orderRequest, Order.class);
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
}
