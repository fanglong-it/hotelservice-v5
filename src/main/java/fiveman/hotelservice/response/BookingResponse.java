package fiveman.hotelservice.response;

import java.util.List;

import fiveman.hotelservice.entities.Customer;
import fiveman.hotelservice.entities.RequestService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class BookingResponse {
   
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

    private String status;

    private String createDate;
    private String updateDate;
    private String createBy;
    private String lastModifyBy;
    private long roomTypeId;

    private long hotel_Id;

    private long room_Id;
    
    private Customer customer;

    private List<RequestService> requestServices;

}
