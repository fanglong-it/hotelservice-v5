package fiveman.hotelservice.response;

import java.util.List;

import fiveman.hotelservice.entities.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewResponse {
    private long id;
    private String newName;
    private String ticketInformation;
    private String detailInformation;
    private String address;
    private String description;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String newType;
    private String status;
    private int numberOfView;
    private long hotel_Id;
    private List<Image> images;

}
