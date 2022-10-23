package fiveman.hotelservice.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
public class BookingRequest {

    @ApiModelProperty(required = true, position = 0)
    private long id;

    private int confirmationNo;
    private String arrivalDate;
    private String actualArrivalDate;
    private String departureDate;
    private String actualDepartureDate;
    private int numOfAdult;
    private int numOfChildren;
    private double totalAmount;
    private String roomPayment;
    private String specialNote;

    private boolean status;

    private String createDate;
    private String updateDate;
    private String createBy;
    private String lastModifyBy;

    @ApiModelProperty(required = true)
    private long hotel_Id;

    @ApiModelProperty(required = true)
    private long room_Id;

    @ApiModelProperty(required = true)
    private long customer_Id;

    @ApiModelProperty(required = true)
    private long bill_Id;
}