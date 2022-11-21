package fiveman.hotelservice.controller;

import javax.validation.Valid;

import fiveman.hotelservice.request.VnPayConfirmRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import fiveman.hotelservice.request.MomoClientRequest;
import fiveman.hotelservice.request.VNPayRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.MomoConfirmResultResponse;
import fiveman.hotelservice.response.MomoResponse;
import fiveman.hotelservice.response.VnPayRes;
import fiveman.hotelservice.service.PaymentService;

import io.swagger.annotations.Api;

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
            return new ResponseEntity<>(response, HttpStatus.OK);
      }

      @PostMapping("/vnpay")
      @PreAuthorize("isAuthenticated() or isAnonymous()")
      public ResponseEntity<VnPayRes> paymentWithVNPAY(@RequestBody @Valid VNPayRequest request) {
            VnPayRes res = paymentService.getPaymentVNPay(request);      
            return ResponseEntity.status(HttpStatus.OK).body(res);
      }

      @PostMapping("/VnPayConfirm")
      @PreAuthorize("isAuthenticated() or isAnonymous()")
      public ResponseEntity<CustomResponseObject> getVNPayConfirm(@RequestBody VnPayConfirmRequest request) {
            CustomResponseObject result = paymentService.validateVNPay(request);
            return new ResponseEntity<CustomResponseObject>(result, HttpStatus.OK);
      }

}
