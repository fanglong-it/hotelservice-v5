package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.RoomPrice;
import fiveman.hotelservice.response.CustomResponseObject;

public interface RoomPriceService {
      List<RoomPrice> getRoomPrices();
      
      RoomPrice getRoomPrice(long id);
      
      CustomResponseObject saveRoomPrice(RoomPrice roomPrice);

      CustomResponseObject updateRoomPrice(RoomPrice roomPrice);

      CustomResponseObject deleteRoomPrice(long id);
}
