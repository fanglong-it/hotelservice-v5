package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.BillPayment;
import fiveman.hotelservice.response.CustomResponseObject;

import java.util.List;

public interface BillPaymentService {
    BillPayment getBillPaymentById(long id);

    List<BillPayment> getAllBillPayment();

    CustomResponseObject saveBillPayment(BillPayment billPayment);

    CustomResponseObject updateBillPayment(BillPayment billPayment);

    CustomResponseObject deleteBillPayment(long id);

}
