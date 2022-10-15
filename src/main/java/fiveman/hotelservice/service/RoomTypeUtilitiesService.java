package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.RoomTypeUtilities;
import fiveman.hotelservice.response.CustomResponseObject;

public interface RoomTypeUtilitiesService {
      List<RoomTypeUtilities> getRoomTypeUtilities();
      
      RoomTypeUtilities getRoomTypeUtilitiesById(long id);
      
      CustomResponseObject addRoomTypeUtilities(RoomTypeUtilities roomTypeUtilities);

      CustomResponseObject updateRoomTypeUtilities(RoomTypeUtilities roomTypeUtilities);

      CustomResponseObject deleteRoomTypeUtilities(long id);
}
