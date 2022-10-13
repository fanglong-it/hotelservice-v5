package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.Bill;
import fiveman.hotelservice.response.CustomResponseObject;

import java.util.List;

public interface BillService {
    Bill getBillById(long id);

    List<Bill> getAllBill();

    CustomResponseObject saveBill(Bill bill);

    CustomResponseObject updateBill(Bill bill);

    CustomResponseObject deleteBillById(long id);
}
