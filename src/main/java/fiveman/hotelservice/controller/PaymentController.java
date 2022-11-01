package fiveman.hotelservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import fiveman.hotelservice.request.MomoClientRequest;
import fiveman.hotelservice.request.VNPayRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.MomoConfirmResultResponse;
import fiveman.hotelservice.response.MomoResponse;
import fiveman.hotelservice.response.VnPayRes;
import fiveman.hotelservice.service.PaymentService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@Api(tags = "Payment")
@RequestMapping("/api/v1")
public class PaymentController {

      @Autowired
      private PaymentService paymentService;
      
      @PostMapping("/momo")
      @PreAuthorize("isAuthenticated() or isAnonymous()")
      public ResponseEntity<MomoResponse> paymentWithMomo(@RequestBody @Valid MomoClientRequest request) {
            return paymentService.getPaymentMomo(request);       
      }

      @GetMapping("/MomoConfirm")
      @PreAuthorize("isAuthenticated() or isAnonymous()")
      public ResponseEntity<MomoConfirmResultResponse> momoConfirm(@RequestBody MomoConfirmResultResponse response){
            // TODO: clarify result and insert into DB
            return new ResponseEntity<>(response, HttpStatus.OK);
      }

      @PostMapping("/vnpay")
      @PreAuthorize("isAuthenticated() or isAnonymous()")
      public ResponseEntity<VnPayRes> paymentWithVNPAY(@RequestBody @Valid VNPayRequest request) {
            VnPayRes res = paymentService.getPaymentVNPay(request);      
            return ResponseEntity.status(HttpStatus.OK).body(res);
      }

      @GetMapping("/VnPayConfirm")
      @PreAuthorize("isAuthenticated() or isAnonymous()")
      public ResponseEntity<CustomResponseObject> getVNPayConfirm(@RequestParam("vnp_Amount") String vnp_Amount,
                  @RequestParam("vnp_BankCode") String vnp_BankCode,
                  @RequestParam("vnp_BankTranNo") String vnp_BankTranNo,
                  @RequestParam("vnp_CardType") String vnp_CardType,
                  @RequestParam("vnp_OrderInfo") String vnp_OrderInfo,
                  @RequestParam("vnp_PayDate") String vnp_PayDate,
                  @RequestParam("vnp_ResponseCode") String vnp_ResponseCode,
                  @RequestParam("vnp_TmnCode") String vnp_TmnCode,
                  @RequestParam("vnp_TransactionNo") String vnp_TransactionNo,
                  @RequestParam("vnp_TransactionStatus") String vnp_TransactionStatus,
                  @RequestParam("vnp_TxnRef") String vnp_TxnRef,
                  @RequestParam("vnp_SecureHash") String vnp_SecureHash) {
            
//            String query = vnp_Amount + " - " + vnp_BankCode + " - " + vnp_BankTranNo + " - " + vnp_CardType + " - "
//                        + vnp_OrderInfo + " - " + vnp_PayDate + " - " + vnp_ResponseCode + " - " +
//                        vnp_SecureHash + " - " + vnp_TmnCode + " - " + vnp_TransactionNo + " - " + vnp_TransactionStatus
//                        + " - " + vnp_TxnRef;
//            System.out.println("query: " + query);
            CustomResponseObject ressult = paymentService.validateVNPay(vnp_OrderInfo, vnp_Amount, vnp_SecureHash, vnp_ResponseCode);
            return new ResponseEntity<CustomResponseObject>(ressult, HttpStatus.OK);
      }

}
