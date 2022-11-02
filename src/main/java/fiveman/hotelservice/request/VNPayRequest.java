package fiveman.hotelservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class VNPayRequest {
      private long vnp_amount;
      private String vnp_BankCode;
//      private String Curr_Code; // đơn vij tiền tệ sử dụng thanh toán ( VND )
      private String vnp_IpAddr; // ip of client
      private String vnp_Locale;
      private String vnp_OrderInfo; // info of order tieng viet k dau
      private String vnp_TxnRef; // orderID
}
