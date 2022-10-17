package fiveman.hotelservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String lastModifyBy;
    private long hotel_Id;
    private long roomType_Id;
}
