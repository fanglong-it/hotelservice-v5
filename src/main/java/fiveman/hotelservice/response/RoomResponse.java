package fiveman.hotelservice.response;

import javax.persistence.ColumnResult;
import javax.persistence.SqlResultSetMapping;

import fiveman.hotelservice.entities.Booking;
import fiveman.hotelservice.entities.Hotel;
import fiveman.hotelservice.entities.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



// @SqlResultSetMapping(name = "RoomResponseMapping",
//         columns = {@ColumnResult(name = "id")})

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomResponse {

    private long id;
    private String name;
    private String roomNo;
    private String description;
    private String createDate;
    private String updateDate;
    private String createBy;
    private boolean status;
    private String lastModifyBy;
    private Hotel hotel;
    private RoomType roomType;
    private Booking booking;
    
    
}
