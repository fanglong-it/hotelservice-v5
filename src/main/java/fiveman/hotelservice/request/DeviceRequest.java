package fiveman.hotelservice.request;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class DeviceRequest {

	@Id
	@ApiModelProperty(required = true)
	private long id;

	@NotBlank(message = "Name are mandatory")
	private String name;

	@NotNull
	private String partNumber;

	@NotNull
	private String serialNo;

	private String brand;

	private String description;

	@NotNull
	private long room_Id;
}
