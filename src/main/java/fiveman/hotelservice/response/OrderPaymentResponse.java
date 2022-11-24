package fiveman.hotelservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderPaymentResponse {
    
    private long id;

    private double paymentAmount;

    private String dateTime;

    private long paymentMethod_Id;
}
