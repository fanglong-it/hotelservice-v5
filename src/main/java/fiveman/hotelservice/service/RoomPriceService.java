package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.RoomPrice;
import fiveman.hotelservice.request.RoomPriceRequest;
import fiveman.hotelservice.response.CustomResponseObject;

public interface RoomPriceService {

      List<RoomPrice> getRoomPrices();

      RoomPrice getRoomPrice(long id);

      RoomPrice saveRoomPrice(RoomPrice roomPrice);

      RoomPrice updateRoomPrice(RoomPrice roomPrice);

      CustomResponseObject deleteRoomPrice(long id);

      CustomResponseObject setRoomPriceByDate(RoomPriceRequest roomPrice);

}
