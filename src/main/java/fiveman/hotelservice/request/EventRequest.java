package fiveman.hotelservice.request;


import fiveman.hotelservice.entities.Hotel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Time;

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
    private boolean status;

    private int numberOfView;

    private long hotel_Id;
}
