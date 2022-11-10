package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.OrderDetail;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.OrderDetailRepository;
import fiveman.hotelservice.repository.ServiceRepository;
import fiveman.hotelservice.response.OrderDetailResponse;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.OrderDetailService;
import fiveman.hotelservice.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ServiceRepository serviceRepository;

    OrderDetailResponse mapBillDetailToResponse(OrderDetail billDetail) {
//        private long id;
//        private long service_Id;
//        private long bill_Id;
//        private int quantity;
//        private double price;
//        private double amount;
//        private int status;
        OrderDetailResponse billDetailResponse
                = new OrderDetailResponse();
        billDetailResponse.setId(billDetail.getId());
        billDetailResponse.setService(serviceRepository.getServiceById(billDetail.getService().getId()));
        billDetailResponse.setOrder_Id(billDetail.getOrder().getId());
        billDetailResponse.setQuantity(billDetail.getQuantity());
        billDetailResponse.setPrice(billDetail.getPrice());
        billDetailResponse.setAmount(billDetail.getAmount());
        billDetailResponse.setOrderDate(billDetail.getOrderDate());
        return billDetailResponse;
    }

    @Override
    public List<OrderDetailResponse> getAllByBill_Id(long Bill_Id) {
        List<OrderDetailResponse> billDetailResponses = new ArrayList<>();
        List<OrderDetail> billDetails = orderDetailRepository.getAllByOrder_Id(Bill_Id);
        for (OrderDetail billDetail :
                billDetails) {
            billDetailResponses.add(mapBillDetailToResponse(billDetail));
        }
        return billDetailResponses;
    }

    @Override
    public OrderDetailResponse getBillDetailById(long id) {
        if (!orderDetailRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Not found id =" + id));
        }
        return mapBillDetailToResponse(orderDetailRepository.getOrderDetailById(id));
    }

    @Override
    public CustomResponseObject deleteBillDetail(long id) {
        if (!orderDetailRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Not found id =" + id));
        }
        orderDetailRepository.deleteById(id);
        return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
    }

    @Override
    public CustomResponseObject saveBillDetail(OrderDetail billDetail) {
        if (orderDetailRepository.existsById(billDetail.getId())) {
            throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" + billDetail.getId()));
        }
        orderDetailRepository.save(billDetail);
        return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding Success!");
    }

    @Override
    public CustomResponseObject updateBillDetail(OrderDetail billDetail) {
        if (!orderDetailRepository.existsById(billDetail.getId())) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL, "Not found id =" + billDetail.getId()));
        }
        orderDetailRepository.save(billDetail);
        return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
    }
}
