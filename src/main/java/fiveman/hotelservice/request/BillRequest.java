package fiveman.hotelservice.request;

import fiveman.hotelservice.entities.Customer;
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
public class BillRequest {
    @ApiModelProperty(required = true)
    private long id;
    private double totalAmount;
    private String createDate;
    private String updateDate;
    private String createBy;
    private String lastModifyBy;
    private long customer_Id;
}
