package fiveman.hotelservice.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import fiveman.hotelservice.entities.ServiceCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Data
@Getter
@Setter
public class ServiceRequest {

    @ApiModelProperty(required = true, position = 0)
    private long id;

    @ApiModelProperty(required = true, position = 1)
    @NotBlank(message = "Name are mandatory")
    private String name;

    @ApiModelProperty(position = 2)
    @NotBlank(message = "picture are mandatory")
    private String picture;

    @ApiModelProperty(position = 3)
    @NotBlank(message = "price are mandatory")
    private double price;

    @ApiModelProperty(position = 4)
    @NotBlank(message = "description are mandatory")
    private String description;

//    @ApiModelProperty(position = 5)
    @NonNull
    private Boolean isExternal;


    private String createDate;
    private String updateDate;
    private String createBy;
    private String updateBy;

    @ApiModelProperty(required = true, position = 10)
    private long serviceCategory_Id;

}
