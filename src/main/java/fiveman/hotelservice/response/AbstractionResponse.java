package fiveman.hotelservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AbstractionResponse {
    private long id;

    private String name;

    private double longtitude;
    private double latidute; 

    private String openTime;
    private String closeTime;
    private String address;
    private String description;

    private Long hotel_Id;

}
