package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.BillDetail;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.BillDetailRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.BillDetailService;
import fiveman.hotelservice.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillDetailServiceImpl implements BillDetailService {

    @Autowired
    BillDetailRepository billDetailRepository;

    @Override
    public List<BillDetail> getAllByBill_Id(long Bill_Id) {
        return billDetailRepository.getAllByBill_Id(Bill_Id);
    }

    @Override
    public BillDetail getBillDetailById(long id) {
        if (!billDetailRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Not found id =" + id));
        }
        return billDetailRepository.getBillDetailById(id);
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
