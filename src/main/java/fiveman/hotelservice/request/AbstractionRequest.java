package fiveman.hotelservice.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Getter
@Setter
public class AbstractionRequest {

    @Id
    @ApiModelProperty(required = true)
    private long id;

    @NotBlank(message = "Name are mandatory")
    private String name;

    private String logtitude;
    private String latidute;

    private String openTime;
    private String closeTime;
    private String address;
    private String description;

    private long hotel_Id;

}
