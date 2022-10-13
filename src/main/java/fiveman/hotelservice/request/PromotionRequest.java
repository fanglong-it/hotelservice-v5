package fiveman.hotelservice.request;

import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class PromotionRequest {

    @Id
    @ApiModelProperty(required = true)
    private long id;

    private String name;
    private String description;
    private String detailInformation;
    private String startDate;
    private String endDate;

    private long hotel_Id;
}
