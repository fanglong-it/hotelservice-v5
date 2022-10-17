package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.Bill;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.BillRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.BillService;
import fiveman.hotelservice.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillRepository billRepository;


    @Override
    public List<Bill> getAllBill() {
        return billRepository.findAll();
    }

    @Override
    public Bill getBillById(long id) {
        if (!billRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Cant found id = " + id));
        }
        return billRepository.getBillById(id);
    }

    @Override
    public CustomResponseObject saveBill(Bill bill) {
        if (billRepository.existsById(bill.getId())) {
            throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist id = " + bill.getId()));
        }
        billRepository.save(bill);
        return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding Success!");
    }

    @Override
    public CustomResponseObject updateBill(Bill bill) {
        if (!billRepository.existsById(bill.getId())) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL, "Not found id = " + bill.getId()));
        }
        billRepository.save(bill);
        return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
    }

    @Override
    public CustomResponseObject deleteBillById(long id) {
        if (!billRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.DELETE_FAIL, "Cant found id = " + id));
        }
        billRepository.deleteById(id);
        return new CustomResponseObject(Common.DELETE_SUCCESS, "Update Success!");

    }
}
