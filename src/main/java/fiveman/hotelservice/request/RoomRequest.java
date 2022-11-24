package fiveman.hotelservice.request;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class RoomRequest {

    @ApiModelProperty(required = true)
    private long id;

    @ApiModelProperty(required = true)
    private String name;

    @ApiModelProperty(required = true)
    private String roomNo;

    private String description;
    private String createDate;
    private String updateDate;
    private String createBy;
    private String lastModifyBy;

    private boolean status;

    @ApiModelProperty(required = true)
    private long hotel_Id;

    @ApiModelProperty(required = true)
    private long roomType_Id;

}
