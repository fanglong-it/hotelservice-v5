package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.PaymentMethod;
import fiveman.hotelservice.response.CustomResponseObject;

import java.util.List;

public interface PaymentMethodService {
    PaymentMethod getPaymentMethodById(long id);

    List<PaymentMethod> getPaymentMethods();

    CustomResponseObject savePaymentMethod(PaymentMethod paymentMethod);

    CustomResponseObject updatePaymentMethod(PaymentMethod paymentMethod);

    CustomResponseObject deletePaymentMethod(long id);
}
