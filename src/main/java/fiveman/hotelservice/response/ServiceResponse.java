package fiveman.hotelservice.response;

import java.util.List;

import fiveman.hotelservice.entities.Image;
import fiveman.hotelservice.entities.ServiceCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceResponse {
    private long id;
    private String name;
    private double price;
    private String description;
    private boolean status;
    private String majorGroup;
    private List<Image> image;
    private String createDate;
    private String updateDate;
    private String createBy;
    private String lastModifyBy;
    private ServiceCategory serviceCategory;

}
