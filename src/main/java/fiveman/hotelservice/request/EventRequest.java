package fiveman.hotelservice.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@Data
@Getter
@Setter
public class EventRequest {

    @ApiModelProperty(required = true)
    private long id;

    @NotBlank(message = "Name are mandatory")
    private String name;

    private String ticketInformation;
    private String address;
    private String description;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String status;

    private int numberOfView;

    private long hotel_Id;
}
