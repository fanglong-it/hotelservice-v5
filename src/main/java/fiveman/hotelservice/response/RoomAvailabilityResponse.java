package fiveman.hotelservice.response;

import java.util.List;

import fiveman.hotelservice.entities.Image;
import fiveman.hotelservice.entities.Room;
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
      private double defaultPrice;
      private boolean isActive;
      private String bedType;
      private int numOfRoom;
      private List<RoomPrice> roomPrices;
      private List<Room> rooms;
      private List<Utilities> utilities;
      private List<Image> images;
}
