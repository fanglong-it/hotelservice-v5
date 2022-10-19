package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.BillPayment;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.BillPaymentRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.BillPaymentService;
import fiveman.hotelservice.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillPaymentServiceImpl implements BillPaymentService {

    @Autowired
    BillPaymentRepository billPaymentRepository;

    @Override
    public BillPayment getBillPaymentById(long id) {
        if (!billPaymentRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Not found id =" + id));
        }
        return billPaymentRepository.getBillPaymentById(id);
    }

    @Override
    public List<BillPayment> getAllBillPayment() {
        return billPaymentRepository.findAll();
    }

    @Override
    public CustomResponseObject saveBillPayment(BillPayment billPayment) {
        if (billPaymentRepository.existsById(billPayment.getId())) {
            throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist id = " + billPayment.getId()));
        }
        billPaymentRepository.save(billPayment);
        return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding Success!");
    }

    @Override
    public CustomResponseObject updateBillPayment(BillPayment billPayment) {
        if (!billPaymentRepository.existsById(billPayment.getId())) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL, "Not found id = " + billPayment.getId()));
        }
        billPaymentRepository.save(billPayment);
        return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
    }

    @Override
    public CustomResponseObject deleteBillPayment(long id) {
        if (!billPaymentRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.DELETE_FAIL, "Not found id =" + id));
        }
        billPaymentRepository.deleteById(id);
        return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
    }

}
