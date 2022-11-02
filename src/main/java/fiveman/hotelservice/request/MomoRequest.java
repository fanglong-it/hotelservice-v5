package fiveman.hotelservice.request;

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
public class MomoRequest {
      private String partnerCode;
      
      private String partnerName; // option
      
      private String storeId;   // option
      
      private String requestId; // == orderId
      
      private String requestType;
      
      private String ipnUrl;
      
      private String redirectUrl;
      
      private String orderId;
      
      private String orderInfo;
      
      private String extraData; // codebase64 - or ""
      
      private boolean autoCapture; // default is true. if false transaction is not auto capture
      
      private String lang; // vi - en
      
      private String signature;
      
      private long amount;
      
      private CustomerInfoMomoRequest userInfo;
      
}
