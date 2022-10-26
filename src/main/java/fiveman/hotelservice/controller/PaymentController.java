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
      public String getVNPayConfirm(@RequestParam("vnp_Amount") String vnp_Amount,
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
//            Map fields = new HashMap();
//            for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
//                String fieldName = (String) params.nextElement();
//                String fieldValue = request.getParameter(fieldName);
//                if ((fieldValue != null) && (fieldValue.length() > 0)) 
//                {
//                    fields.put(fieldName, fieldValue);
//                }
//            }
//
//            String vnp_SecureHash = request.getParameter("vnp_SecureHash");
//            if (fields.containsKey("vnp_SecureHashType")) 
//            {
//                fields.remove("vnp_SecureHashType");
//            }
//            if (fields.containsKey("vnp_SecureHash")) 
//            {
//                fields.remove("vnp_SecureHash");
//            }
//            
            // Check checksum
//            String signValue = Config.hashAllFields(fields);
//            if (signValue.equals(vnp_SecureHash)) 
//            {
//
//                boolean checkOrderId = true; // vnp_TxnRef exists in your database
//                boolean checkAmount = true; // vnp_Amount is valid (Check vnp_Amount VNPAY returns compared to the amount of the code (vnp_TxnRef) in the Your database).
//                boolean checkOrderStatus = true; // PaymnentStatus = 0 (pending)
//                
//                
//                if(checkOrderId)
//                {
//                    if(checkAmount)
//                    {
//                        if (checkOrderStatus)
//                        {
//                            if ("00".equals(request.getParameter("vnp_ResponseCode")))
//                            {
//                                
//                                //Here Code update PaymnentStatus = 1 into your Database
//                            }
//                            else
//                            {
//                                
//                                // Here Code update PaymnentStatus = 2 into your Database
//                            }
//                            System.out.print ("{\"RspCode\":\"00\",\"Message\":\"Confirm Success\"}");
//                        }
//                        else
//                        {
//                            
//                              System.out.print("{\"RspCode\":\"02\",\"Message\":\"Order already confirmed\"}");
//                        }
//                    }
//                    else
//                    {
//                          System.out.print("{\"RspCode\":\"04\",\"Message\":\"Invalid Amount\"}"); 
//                    }
//                }
//                else
//                {
//                      System.out.print("{\"RspCode\":\"01\",\"Message\":\"Order not Found\"}");
//                }
//            } 
//            else 
//            {
//                  System.out.print("{\"RspCode\":\"97\",\"Message\":\"Invalid Checksum\"}");
//            }
//        }
//        catch(Exception e)
//        {
//              System.out.print("{\"RspCode\":\"99\",\"Message\":\"Unknow error\"}");
//        }
            String query = vnp_Amount + " - " + vnp_BankCode + " - " + vnp_BankTranNo + " - " + vnp_CardType + " - "
                        + vnp_OrderInfo + " - " + vnp_PayDate + " - " + vnp_ResponseCode + " - " +
                        vnp_SecureHash + " - " + vnp_TmnCode + " - " + vnp_TransactionNo + " - " + vnp_TransactionStatus
                        + " - " + vnp_TxnRef;
            return query;
      }

}
