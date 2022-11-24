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
import fiveman.hotelservice.request.MomoClientRequest;
import fiveman.hotelservice.request.VNPayRequest;
import fiveman.hotelservice.service.OrderPaymentService;
import fiveman.hotelservice.service.OrderService;
import fiveman.hotelservice.service.PaymentMethodService;
import fiveman.hotelservice.service.PaymentService;
import fiveman.hotelservice.utils.Common;
import io.swagger.annotations.Api;

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
      PaymentMethodService paymentMethodService;

      @GetMapping("/MomoConfirm")
      @PreAuthorize("isAuthenticated() or isAnonymous()")
      public ResponseEntity<MomoConfirmResultResponse> momoConfirm(
                  @RequestParam("partnerCode") String partnerCode,
                  @RequestParam("orderId") long orderId,
                  @RequestParam("requestId") long requestId,
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

                  // Order order = orderService.getBillById(orderId);
                  // if (!orderPaymentService.existOrderPaymentByOrderId(orderId)) {
                  //       order.setStatus("1");
                  //       orderService.updateBill(order);
                  //       OrderPayment orderPayment = new OrderPayment();
                  //       orderPayment.setId(0);
                  //       // orderPayment.setOrder(order);
                  //       PaymentMethod pay = paymentMethodService.getPaymentMethodById(1);
                  //       orderPayment.setPaymentMethod(pay);
                  //       orderPayment.setPaymentAmount(order.getTotalAmount());
                  //       orderPayment.setDateTime(
                  //                   fiveman.hotelservice.utils.Utilities.getCurrentDateByFormat("dd/MM/yyyy"));
                  //       orderPaymentService.saveOrderPayment(orderPayment);
                  // }
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
