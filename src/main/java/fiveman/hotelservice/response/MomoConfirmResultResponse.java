package fiveman.hotelservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class MomoConfirmResultResponse {
      private String partnerCode;
      private String orderId;
      private String requestId;
      private long amount;
      private String orderInfo;
      private String orderType;
      private long transId;
      private int resultCode;
      private String message;
      private String payType;
      private long responseTime;
      private String extraData;
      private String sognature;
}
