package fiveman.hotelservice.service;


import fiveman.hotelservice.entities.OrderDetail;
import fiveman.hotelservice.response.OrderDetailResponse;
import fiveman.hotelservice.response.CustomResponseObject;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailResponse> getAllByBill_Id(long Bill_Id);
    
    OrderDetailResponse getBillDetailById(long id);

    CustomResponseObject deleteBillDetail(long id);

    CustomResponseObject saveBillDetail(OrderDetail billDetail);

    CustomResponseObject updateBillDetail(OrderDetail billDetail);
}
