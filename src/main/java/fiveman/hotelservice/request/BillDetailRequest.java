package fiveman.hotelservice.request;

import fiveman.hotelservice.entities.Bill;
import fiveman.hotelservice.entities.Service;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    private int quantity;
    private double price;
    private double amount;
    private int Status;

}
