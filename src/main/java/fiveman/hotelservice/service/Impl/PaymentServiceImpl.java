package fiveman.hotelservice.service.Impl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import fiveman.hotelservice.entities.BillDetail;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.request.MomoClientRequest;
import fiveman.hotelservice.request.MomoRequest;
import fiveman.hotelservice.request.VNPayRequest;
import fiveman.hotelservice.response.BillDetailResponse;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.MomoResponse;
import fiveman.hotelservice.response.VnPayRes;
import fiveman.hotelservice.service.BillDetailService;
import fiveman.hotelservice.service.PaymentService;
import fiveman.hotelservice.utils.Common;
import fiveman.hotelservice.utils.Utilities;

@Service
public class PaymentServiceImpl implements PaymentService {

      
      @Autowired
      private BillDetailService billDetailService;
      
      @Autowired
      private ModelMapper mapper;

      @Override
      public ResponseEntity<MomoResponse> getPaymentMomo(MomoClientRequest request) {
            // request url
            String url = Common.MOMO_URI;

            // create an instance of RestTemplate
            RestTemplate restTemplate = new RestTemplate();

            // create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            // create a post object

            // build the request
            MomoRequest momoReq = new MomoRequest();
//            CustomerInfoMomoRequest customerInfo = new CustomerInfoMomoRequest("dat", "0123456789", "dat@gmail.com");

            // long amount = 200000;
            
            String sign = "accessKey=" + Common.ACCESS_KEY + "&amount=" + 10000 + "&extraData="
                        + "&ipnUrl=" + Common.IPN_URL_MOMO + "&orderId=" + request.getOrderId() + "&orderInfo="
                        + request.getOrderInfo()
                        + "&partnerCode=" + Common.PARTNER_CODE + "&redirectUrl=" + Common.REDIRECT_URL_MOMO
                        + "&requestId=" + request.getOrderId() + "&requestType=captureWallet";

            String signatureHmac = "";
            try {
                  signatureHmac = Utilities.calculateHMac(sign, Common.HMACSHA256, Common.SECRET_KEY);
                  System.out.println("signature: " + signatureHmac + "   ");
            } catch (Exception e) {
                  throw new AppException(HttpStatus.BAD_REQUEST.value(),
                              new CustomResponseObject(Common.ADDING_FAIL, "pay fail"));
            }

            momoReq.setPartnerCode(Common.PARTNER_CODE);
            momoReq.setExtraData("");
            momoReq.setSignature(signatureHmac);
            momoReq.setAmount(10000);
            momoReq.setExtraData("");
            momoReq.setIpnUrl(Common.IPN_URL_MOMO);
            momoReq.setLang(request.getLang());
            momoReq.setOrderId(request.getOrderId());
            momoReq.setOrderInfo(request.getOrderInfo());
            momoReq.setRedirectUrl(Common.REDIRECT_URL_MOMO);
            momoReq.setRequestId(request.getOrderId());
            momoReq.setRequestType("captureWallet");
            momoReq.setStoreId(request.getStoreId());

            HttpEntity<MomoRequest> req = new HttpEntity<>(momoReq, headers);

            // send POST request
            try {
                  ResponseEntity<MomoResponse> response = restTemplate.postForEntity(url, req, MomoResponse.class);
                  if (response != null) {
                        return response;
                  }
            } catch (Exception e) {
                  String arr[] = String.valueOf(e.getMessage()).split(",");
                  String ar[] = arr[1].split(":");
                  String message = ar[1].replaceAll("\"", "");
                  throw new AppException(HttpStatus.BAD_REQUEST.value(),
                              new CustomResponseObject(Common.ADDING_FAIL, message));
            }

            return null;
      }

      @Override
      public VnPayRes getPaymentVNPay(VNPayRequest request) {
            // get system param
            String vnp_Version = Common.VNP_VERSION;
            String vnp_Command = Common.VNP_COMMAND;
            String vnp_TmnCode = Common.VNP_TMNCODE;

            // get request param
            long amount = request.getVnp_amount() * 100;

            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", vnp_Version);
            vnp_Params.put("vnp_Command", vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(amount));
            vnp_Params.put("vnp_CurrCode", "VND");

            String bank_code = request.getVnp_BankCode();
            if (bank_code != null && !bank_code.isEmpty()) {
                  vnp_Params.put("vnp_BankCode", bank_code);
            }
            vnp_Params.put("vnp_TxnRef", request.getVnp_TxnRef());
            vnp_Params.put("vnp_OrderInfo", request.getVnp_OrderInfo());
            vnp_Params.put("vnp_OrderType", Common.VNP_ORDER_TYPE_HOTEL);

            String locale = request.getVnp_Locale();
            if (locale != null && !locale.isEmpty()) {
                  vnp_Params.put("vnp_Locale", locale);
            } else {
                  vnp_Params.put("vnp_Locale", "vn");
            }

            vnp_Params.put("vnp_ReturnUrl", Common.VNP_RETURNURL);
            vnp_Params.put("vnp_IpAddr", request.getVnp_IpAddr());

            String vnp_CreateDate = Utilities.getCurrentDateByFormat("yyyyMMddHHmmss");

            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

            String vnp_ExpireDate = Utilities.getExpireDate("yyyyMMddHHmmss");
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

            // get query String with param in hash map
            List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator<String> itr = fieldNames.iterator();

            try {
                  while (itr.hasNext()) {
                        String fieldName = (String) itr.next();
                        String fieldValue = (String) vnp_Params.get(fieldName);
                        if ((fieldValue != null) && (fieldValue.length() > 0)) {
                              // Build hash data
                              hashData.append(fieldName);
                              hashData.append('=');
                              hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                              // Build query
                              query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                              query.append('=');
                              query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                              if (itr.hasNext()) {
                                    query.append('&');
                                    hashData.append('&');
                              }
                        }
                  }
            } catch (Exception e) {
                  e.printStackTrace();
            }
            String queryUrl = query.toString();
            String vnp_SecureHash = Utilities.hmacSHA512(Common.VNP_HASHSCRET, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = Common.VNP_URI + "?" + queryUrl;

            VnPayRes res = new VnPayRes();
            res.setCode("00");
            res.setMessage("sucess");
            res.setUrl(paymentUrl);

            return res;
      }

      @Override
      public CustomResponseObject validateVNPay(String billId, String amount, String secureHash, String responseCode) {
            boolean checkEmptyId = Utilities.isEmptyString(billId);
            boolean checkEmptyAmount = Utilities.isEmptyString(amount);
            if (!checkEmptyId && !checkEmptyAmount) {
//                  Bill bill = billService.getBillById(Long.parseLong(billId));
//                  if (bill != null) {
//                        double bill_amount = bill.getTotalAmount();
//                        if (Double.parseDouble(amount) == bill_amount) {
//                              List<BillDetailResponse> list = billDetailService.getAllByBill_Id(bill.getId());
//                              // update status
//                              payLibs(list);
//                              // save all bill detail into DB
//                              // save bill into DB
//                              return new CustomResponseObject(responseCode, "Payment success with bill_id: " + billId);
//                        } else {
//                              new AppException(HttpStatus.BAD_REQUEST.value(),
//                                          new CustomResponseObject(responseCode, "Invalid Amount"));
//                        }
//                  } else {
//                        new AppException(HttpStatus.BAD_REQUEST.value(),
//                                    new CustomResponseObject(responseCode, "Bill Not Found"));
//                  }
                  return new CustomResponseObject(responseCode, "Payment success with bill_id: " + billId);
            }

            return null;
      }
      
      public void payLibs(List<BillDetailResponse> list) {
            for (BillDetailResponse billDetailResponse : list) {
                  BillDetail billDetail = mapper.map(billDetailResponse, BillDetail.class);
                  billDetail.setStatus(1);
                  billDetailService.updateBillDetail(billDetail);
            }
      }

}
