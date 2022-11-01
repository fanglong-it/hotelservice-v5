package fiveman.hotelservice.service;


import fiveman.hotelservice.entities.BillDetail;
import fiveman.hotelservice.response.BillDetailResponse;
import fiveman.hotelservice.response.CustomResponseObject;

import java.util.List;

public interface BillDetailService {
    List<BillDetailResponse> getAllByBill_Id(long Bill_Id);
    
    BillDetailResponse getBillDetailById(long id);

    CustomResponseObject deleteBillDetail(long id);

    CustomResponseObject saveBillDetail(BillDetail billDetail);

    CustomResponseObject updateBillDetail(BillDetail billDetail);
}
