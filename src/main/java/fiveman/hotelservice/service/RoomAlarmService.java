package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.RoomAlarm;
import fiveman.hotelservice.response.CustomResponseObject;

public interface RoomAlarmService {
      List<RoomAlarm> getAllRoomAlarm();
      
      RoomAlarm getRoomAlarm(long id);
      
      CustomResponseObject saveRoomAlarm(RoomAlarm roomAlarm);
      
      CustomResponseObject updateRoomAlarm(RoomAlarm roomAlarm);
      
      CustomResponseObject deleteRoomAlarm(long id);
}
