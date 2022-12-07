package fiveman.hotelservice.response;

import java.util.List;

import fiveman.hotelservice.entities.Image;
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
public class ServiceCategoryResponse {
   private long id;
   private String name;
   private String description;
   boolean foodAndBeverage;
   boolean ordered;
   boolean status;
   private List<Image> images;
}
