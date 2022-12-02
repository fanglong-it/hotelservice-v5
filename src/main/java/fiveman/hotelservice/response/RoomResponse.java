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



@SqlResultSetMapping(name = "RoomResponseMapping",
        columns = {@ColumnResult(name = "id")})
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
    
    public RoomResponse(long id, String name, String roomNo, String description, String createDate, String updateDate,
            String createBy, boolean status, String lastModifyBy, Hotel hotel, RoomType roomType, Booking booking) {
        this.id = id;
        this.name = name;
        this.roomNo = roomNo;
        this.description = description;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createBy = createBy;
        this.status = status;
        this.lastModifyBy = lastModifyBy;
        this.hotel = hotel;
        this.roomType = roomType;
        this.booking = booking;
    }

    
    // private String primaryCustomer;
    
}
