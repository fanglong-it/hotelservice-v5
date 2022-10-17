package fiveman.hotelservice.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;


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

}
