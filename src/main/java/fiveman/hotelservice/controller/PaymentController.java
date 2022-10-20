package fiveman.hotelservice.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.request.CustomerInfoMomoRequest;
import fiveman.hotelservice.request.ItemRequest;
import fiveman.hotelservice.request.MomoClientRequest;
import fiveman.hotelservice.request.MomoRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.MomoResponse;
import fiveman.hotelservice.utils.Common;
import fiveman.hotelservice.utils.Utilities;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@Api(tags = "Payment")
@RequestMapping("/api/v1")
public class PaymentController {
      
      @GetMapping("/getResultMomo")
      @PreAuthorize("isAuthenticated() or isAnonymous()")
      public String getResult(){
            return "getResult";
      }
      
      @PostMapping("/momo")
      @PreAuthorize("isAuthenticated() or isAnonymous()")
      public ResponseEntity<MomoResponse> paymentWithMomo(@RequestBody @Valid MomoClientRequest request){
            
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
            CustomerInfoMomoRequest customerInfo = new CustomerInfoMomoRequest("dat", "0123456789", "dat@gmail.com");
            
            // signature example
            // String signature = "accessKey=WehkypIRwPP14mHb&amount=15000&extraData=&ipnUrl=https://momo.vn&orderId=MM1540456472575&orderInfo=test&partnerCode=MOMODJMX20220717&redirectUrl=https://momo.vn&requestId=MM1540456472575&requestType=captureWallet";
            
            String sign = "accessKey="+ Common.ACCESS_KEY +"&amount="+ request.getAmount() + "&extraData="
                        + "&ipnUrl=" + request.getIpnUrl() + "&orderId="+ request.getOrderId() +"&orderInfo="+ request.getOrderInfo()
                        + "&partnerCode="+ Common.PARTNER_CODE + "&redirectUrl=" + request.getRedirectUrl()
                        + "&requestId="+  request.getRequestId() + "&requestType=captureWallet";
            
            System.out.println("signature: " + sign + "   ");
            System.out.println("amount: " + request.getAmount() + "   ");
            
            ItemRequest item1 = new ItemRequest("1","yomost",5500,2,11000);
            ItemRequest item2 = new ItemRequest("2","nutrifood",5500,2,11000);
            ItemRequest item3 = new ItemRequest("3","vinamilk",5500,2,11000);
            
            List<ItemRequest> list = new ArrayList<ItemRequest>();
            list.add(item1);
            list.add(item2);
            list.add(item3);
            
            String signatureHmac = "";
            try {
                  signatureHmac = Utilities.calculateHMac(sign);
                  System.out.println("signature: " + signatureHmac + "   ");
            } catch (Exception e) {
                  throw new AppException(HttpStatus.BAD_REQUEST.value(), new CustomResponseObject(Common.ADDING_FAIL, "pay fail"));
            }
                               
            momoReq.setUserInfo(customerInfo);
            momoReq.setPartnerCode(Common.PARTNER_CODE);
            momoReq.setSignature(signatureHmac); //e10f8c1f8482efee10a0216abdb5e6dcafc46d7a0b31f30d660c1679c82187d8
            momoReq.setItems(list);
            momoReq.setAmount(request.getAmount());
            momoReq.setAutoCapture(request.isAutoCapture());
            String extraData = request.getExtraData() == null ? "" : request.getExtraData();
            momoReq.setExtraData(extraData);
            momoReq.setIpnUrl(request.getIpnUrl());
            momoReq.setLang(request.getLang());
            momoReq.setOrderId(request.getOrderId());
            momoReq.setOrderInfo(request.getOrderInfo());
            momoReq.setPartnerName(request.getPartnerName());
            momoReq.setRedirectUrl(request.getRedirectUrl());
            momoReq.setRequestId(request.getRequestId());
            momoReq.setRequestType("captureWallet");
            momoReq.setStoreId(request.getStoreId());
           
            
            HttpEntity<MomoRequest> req = new HttpEntity<>(momoReq, headers);
            
            // send POST request
            ResponseEntity<MomoResponse> response = restTemplate.postForEntity(url, req, MomoResponse.class);// url - object req  - response type

            return response;
      }
}
