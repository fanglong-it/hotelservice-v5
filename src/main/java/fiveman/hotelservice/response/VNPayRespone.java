package fiveman.hotelservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VNPayRespone {
      private String vnp_TmnCode;
      
      private long vnp_Amount;
      
      private String vnp_BankCode;
      
      private String vnp_BankTranNo; //option
      
      private String vnp_CardType; // ATM, IB, ACC, QRCODE
      
      private long vnp_PayDate;
      
      private String vnp_OrderInfo;
      
      private long vnp_TransactionNo;
      
      private int vnp_ResponseCode;
      
      private String vnp_TxnRef;
      
      private String vnp_SecureHashType; // SHA256 HmacSHA512
      
      private String vnp_SecureHash; // check sum
}
