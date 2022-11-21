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
      private String signature;
      private String accessKey;

//       "partnerCode": "MOMODJMX20220717",
//     "orderId": "26",
//     "requestId": "67326087-b535-478f-8cd9-cb93593da19a",
//     "amount": 100000,
//     "responseTime": 1668769307259,
//     "message": "Giao dịch thành công.",
//     "resultCode": 0,
//     "payUrl": "https://test-payment.momo.vn/v2/gateway/pay?t=TU9NT0RKTVgyMDIyMDcxN3wyNg=="

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
}
