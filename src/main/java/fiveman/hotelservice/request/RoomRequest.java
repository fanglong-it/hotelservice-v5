package fiveman.hotelservice.request;

import javax.persistence.Id;

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

	@Id
	@ApiModelProperty(required = true)
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
