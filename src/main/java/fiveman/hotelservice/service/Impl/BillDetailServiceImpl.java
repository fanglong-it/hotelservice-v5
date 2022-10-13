package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.BillDetail;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.BillDetailRepository;
import fiveman.hotelservice.response.BillDetailResponse;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.BillDetailService;
import fiveman.hotelservice.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillDetailServiceImpl implements BillDetailService {

    @Autowired
    BillDetailRepository billDetailRepository;

    BillDetailResponse mapBillDetailToResponse(BillDetail billDetail) {
//        private long id;
//        private long service_Id;
//        private long bill_Id;
//        private int quantity;
//        private double price;
//        private double amount;
//        private int status;
        BillDetailResponse billDetailResponse
                = new BillDetailResponse();
        billDetailResponse.setId(billDetail.getId());
        billDetailResponse.setService_Id(billDetail.getService().getId());
        billDetailResponse.setBill_Id(billDetail.getBill().getId());
        billDetailResponse.setQuantity(billDetail.getQuantity());
        billDetailResponse.setPrice(billDetail.getPrice());
        billDetailResponse.setAmount(billDetail.getAmount());
        billDetailResponse.setStatus(billDetail.getStatus());
        return billDetailResponse;
    }

    @Override
    public List<BillDetailResponse> getAllByBill_Id(long Bill_Id) {
        List<BillDetailResponse> billDetailResponses = new ArrayList<>();
        List<BillDetail> billDetails = billDetailRepository.getAllByBill_Id(Bill_Id);
        for (BillDetail billDetail :
                billDetails) {
            billDetailResponses.add(mapBillDetailToResponse(billDetail));
        }
        return billDetailResponses;
    }

    @Override
    public BillDetailResponse getBillDetailById(long id) {
        if (!billDetailRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Not found id =" + id));
        }
        return mapBillDetailToResponse(billDetailRepository.getBillDetailById(id));
    }

    @Override
    public CustomResponseObject deleteBillDetail(long id) {
        if (!billDetailRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Not found id =" + id));
        }
        billDetailRepository.deleteById(id);
        return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
    }

    @Override
    public CustomResponseObject saveBillDetail(BillDetail billDetail) {
        if (billDetailRepository.existsById(billDetail.getId())) {
            throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" + billDetail.getId()));
        }
        billDetailRepository.save(billDetail);
        return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding Success!");
    }

    @Override
    public CustomResponseObject updateBillDetail(BillDetail billDetail) {
        if (!billDetailRepository.existsById(billDetail.getId())) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL, "Not found id =" + billDetail.getId()));
        }
        billDetailRepository.save(billDetail);
        return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
    }
}
