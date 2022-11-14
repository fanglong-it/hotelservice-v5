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
public class RoomAvailableResponse {
      private long id;
      private String name;
      private String roomNo;
      private String description;
      private String createDate;
      private String updateDate;
      private String createBy;
      private String lastModifyBy;
      private List<Image> images;
}



