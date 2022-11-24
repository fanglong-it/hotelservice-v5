package fiveman.hotelservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MomoResponse {
      
      private String partnerCode;
      
      private String orderId;
      
      private String requestId;
      
      private long amount;
      
      private String responseTime;
      
      private String message;
      
      private int resultCode;
      
      private String payUrl;
      
      private String deeplink;
           
      private String qrCodeUrl;
      
      private String deeplinkMiniApp;

      // private String signature;
      
}
