package fiveman.hotelservice.service.Impl;


import fiveman.hotelservice.entities.PaymentMethod;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.PaymentMethodRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.PaymentMethodService;
import fiveman.hotelservice.utils.Common;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    @Override
    public PaymentMethod getPaymentMethodById(long id) {
        if (!paymentMethodRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Not found id = " + id));
        }
        return paymentMethodRepository.getPaymentMethodById(id);
    }

    @Override
    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    @Override
    public CustomResponseObject savePaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethodRepository.existsById(paymentMethod.getId())) {
            throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" + paymentMethod.getId()));
        }
        paymentMethodRepository.save(paymentMethod);
        return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding Success!");
    }

    @Override
    public CustomResponseObject updatePaymentMethod(PaymentMethod paymentMethod) {
        if (!paymentMethodRepository.existsById(paymentMethod.getId())) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL, "Not found id = " + paymentMethod.getId()));
        }
        paymentMethodRepository.save(paymentMethod);
        return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
    }

    @Override
    public CustomResponseObject deletePaymentMethod(long id) {
        if (!paymentMethodRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.DELETE_FAIL, "Not found id = " + id));
        }
        paymentMethodRepository.deleteById(id);
        return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
    }
}
