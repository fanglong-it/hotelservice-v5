package fiveman.hotelservice.service;

import fiveman.hotelservice.request.VnPayConfirmRequest;
import org.springframework.http.ResponseEntity;

import fiveman.hotelservice.request.MomoClientRequest;
import fiveman.hotelservice.request.VNPayRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.MomoResponse;
import fiveman.hotelservice.response.VnPayRes;

public interface PaymentService {
      ResponseEntity<MomoResponse> getPaymentMomo(MomoClientRequest request);
      
      VnPayRes getPaymentVNPay(VNPayRequest request);
      
      CustomResponseObject validateVNPay(VnPayConfirmRequest request);
}
