package fiveman.hotelservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class RoomTypeRequest {
      private long id;
      private String name;
      private String description;
      private int maxOccupancy;
      private int maxAdult;
      private int maxChildren;
      private int defaultOccupancy;
      private boolean isActive;
      private double defaultPrice;
      private String bedType;
}
