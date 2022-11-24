package fiveman.hotelservice.controller;

import javax.validation.Valid;

import fiveman.hotelservice.entities.*;
import fiveman.hotelservice.request.VnPayConfirmRequest;
import fiveman.hotelservice.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


// import fiveman.hotelservice.entities.Order;

import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.OrderPaymentRepository;
import fiveman.hotelservice.request.MomoClientRequest;
import fiveman.hotelservice.request.VNPayRequest;
import fiveman.hotelservice.service.OrderPaymentService;
import fiveman.hotelservice.service.OrderService;
import fiveman.hotelservice.service.PaymentMethodService;
import fiveman.hotelservice.service.PaymentService;
import fiveman.hotelservice.utils.Common;
import fiveman.hotelservice.utils.Utilities;
import io.swagger.annotations.Api;

import java.util.ArrayList;
import java.util.List;

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

      // MomoConfirm?
      // partnerCode=MOMODJMX20220717
      // &orderId=28
      // &requestId=28
      // &amount=60000
      // &orderInfo=Thanh+toan+momo
      // &orderType=momo_wallet
      // &transId=2796877097
      // &resultCode=9000
      // &message=Giao+dịch+đã+được+xác+nhận+thành+công.
      // &payType=qr
      // &responseTime=1669051628444
      // &extraData=
      // &signature=e4718845300c789200360f243e9e690f358c50b5c2c839df5b7fcbb090f70ef2

      @Autowired
      OrderService orderService;

      @Autowired
      OrderPaymentService orderPaymentService;

      @Autowired
      OrderPaymentRepository orderPaymentRepository;


      @Autowired
      PaymentMethodService paymentMethodService;

      @GetMapping("/MomoConfirm")
      @PreAuthorize("isAuthenticated() or isAnonymous()")
      public ResponseEntity<MomoConfirmResultResponse> momoConfirm(
                  @RequestParam("partnerCode") String partnerCode,
                  @RequestParam("orderId") String orderId,
                  @RequestParam("requestId") String requestId,
                  @RequestParam("amount") long amount,
                  @RequestParam("orderInfo") String orderInfo,
                  @RequestParam("orderType") String orderType,
                  @RequestParam("transId") long transId,
                  @RequestParam("resultCode") int resultCode,
                  @RequestParam("message") String message,
                  @RequestParam("payType") String payType,
                  @RequestParam("responseTime") String responseTime,
                  @RequestParam("extraData") String extraData,
                  @RequestParam("signature") String signature) {
            String sign = "accessKey=" +
                        Common.ACCESS_KEY + "&orderId=" + orderId + "&partnerCode=" + Common.PARTNER_CODE
                        + "&requestId=" + requestId;
            String signatureHmac = "";
            try {
                  signatureHmac = fiveman.hotelservice.utils.Utilities.calculateHMac(sign, Common.HMACSHA256, Common.SECRET_KEY);
                  System.out.println("signature: " + signatureHmac + "   ");
            } catch (Exception e) {
                  throw new AppException(HttpStatus.BAD_REQUEST.value(),
                              new CustomResponseObject(Common.ADDING_FAIL, "Signature bị lỗi"));
            }
            
            MomoConfirmResultResponse momoConfirmResultResponse = new MomoConfirmResultResponse(
                        partnerCode, orderInfo, responseTime, amount, orderInfo, orderType, transId,
                        resultCode, message, payType, resultCode, extraData, signatureHmac, Common.PARTNER_CODE);

            String msg = "";
            if (resultCode == 0) {
                  // System.out.println("Giao Dich Thanh cong");
                  msg = "giao dich thanh cong";
            } else if (resultCode == 9000) {
                  msg = "giao dich duoc xac nhan, giao dich thang cong!";

                  OrderPayment orderPayment = new OrderPayment(0, Double.parseDouble("" + amount), Utilities.getCurrentDateByFormat("dd/MM/yyyy"), paymentMethodService.getPaymentMethodById(1));
                  orderPaymentRepository.save(orderPayment);
                  orderPayment = orderPaymentRepository.findTopByOrderByIdDesc();

                  String[] listOrderId = orderId.split("-");
                  List<Order> orders = new ArrayList<>();
                  for (int i = 0; i < listOrderId.length; i++) {
                        Order order = orderService.getBillById(Long.parseLong(listOrderId[i]));
                        orders.add(order);
                  }
                  for (Order order : orders) {
                        order.setOrderPayment(orderPayment);
                        orderService.updateBill(order);
                  }
            }
            System.out.println(resultCode);
            System.out.println(msg);
            // accessKey=WehkypIRwPP14mHb&orderId=23&partnerCode=MOMODJMX20220717&requestId=48468005-6de1-4140-839f-5f2d8d77a001
            return new ResponseEntity<>(momoConfirmResultResponse, HttpStatus.OK);
      }

      @PostMapping("/vnpay")
      @PreAuthorize("isAuthenticated() or isAnonymous()")
      public ResponseEntity<VnPayRes> paymentWithVNPAY(@RequestBody @Valid VNPayRequest request) {
            VnPayRes res = paymentService.getPaymentVNPay(request);
            return ResponseEntity.status(HttpStatus.OK).body(res);
      }

      @PostMapping("/VnPayConfirm")
      @PreAuthorize("isAuthenticated() or isAnonymous()")
      public ResponseEntity<List<BookingResponse>> getVNPayConfirm(@RequestBody VnPayConfirmRequest request) {
            List<BookingResponse> result = paymentService.validateVNPay(request);
            return new ResponseEntity<List<BookingResponse>>(result, HttpStatus.OK);
      }

}
