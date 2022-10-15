package fiveman.hotelservice.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@Data
@AllArgsConstructor
@Getter
@Setter
public class BillDetailRequest {
    @ApiModelProperty(required = true)
    private long id;

    @ApiModelProperty(required = true)
    private long service_Id;

    @ApiModelProperty(required = true)
    private long bill_Id;

    @ApiModelProperty(required = true)
    private int quantity;

    @ApiModelProperty(required = true)
    private double price;

    @ApiModelProperty(required = true)
    private double amount;

    @ApiModelProperty(required = true)
    private int status;

}
