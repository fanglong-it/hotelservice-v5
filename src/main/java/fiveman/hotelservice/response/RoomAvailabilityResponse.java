package fiveman.hotelservice.response;

import java.util.List;

import fiveman.hotelservice.entities.RoomPrice;
import fiveman.hotelservice.entities.Utilities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomAvailabilityResponse{
      private long id;
      private String name;
      private String description;
      private int maxOccupancy;
      private int maxAdult;
      private int maxChildren;
      private int defaultOccupancy;
      private boolean isActive;
      private List<RoomPrice> roomPrices;
      private List<RoomAvailableResponse> rooms;
      private List<Utilities> utilities;
}
