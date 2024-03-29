package fiveman.hotelservice.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailRequest {

    @ApiModelProperty(required = true)
    private long id;
    
    @ApiModelProperty(required = true)
    private long service_Id;

    @ApiModelProperty(required = true)
    private long order_Id;

    @ApiModelProperty(required = true)
    private int quantity;

    @ApiModelProperty(required = true)
    private double price;

    @ApiModelProperty(required = true)
    private double amount;

    private String orderDate;

}
