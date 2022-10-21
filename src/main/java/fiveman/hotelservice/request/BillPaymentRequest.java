package fiveman.hotelservice.request;

import io.swagger.annotations.ApiModelProperty;
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
public class BillPaymentRequest {
    
    @ApiModelProperty(required = true)
    private long id;

    @ApiModelProperty(required = true)
    private double paymentAmount;


    @ApiModelProperty(required = true)
    private String dateTime;

    @ApiModelProperty(required = true)
    private long paymentMethod_Id;

    @ApiModelProperty(required = true)
    private long bill_Id;
    
}
