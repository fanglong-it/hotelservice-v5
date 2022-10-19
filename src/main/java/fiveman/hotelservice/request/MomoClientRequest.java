package fiveman.hotelservice.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
public class MomoClientRequest {
      
      
    
 
      private String partnerName; // option
      
      private String storeId;   // option
      
      private String requestId; // == orderId
      
      private String ipnUrl;
      
      private String redirectUrl;
      
      private long amount;
      
      private String orderId;
      
      private String orderInfo;
      
      private String extraData; // codebase64 - or ""
      
      
      private boolean autoCapture; // default is true. if false transaction is not auto capture
      
      private String lang; // vi - en
      
//      private String signature; // encode by Hmac_SHA256 with string 
                 /*
                  accessKey=$accessKey
                  &amount=$amount
                  &extraData=$extraData
                  &ipnUrl=$ipnUrl
                  &orderId=$orderId
                  &orderInfo=$orderInfo
                  &partnerCode=$partnerCode
                  &redirectUrl=$redirectUrl
                  &requestId=$requestId
                  &requestType=$requestType 
                 */

}
