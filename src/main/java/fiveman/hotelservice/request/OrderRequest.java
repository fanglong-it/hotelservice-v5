package fiveman.hotelservice.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@Data
@AllArgsConstructor
@Getter
@Setter
public class OrderRequest {

    @ApiModelProperty(required = true)
    private long id;

    @ApiModelProperty(required = true)
    private long booking_Id;

    private double totalAmount;
    private String createDate;
    private String updateDate;
    private String createBy;
    private String lastModifyBy;
    private String status;

}
