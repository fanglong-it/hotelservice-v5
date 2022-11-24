package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.RoomAlarm;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.RoomAlarmResponse;

public interface RoomAlarmService {
      List<RoomAlarmResponse> getAllRoomAlarm();
      
      RoomAlarmResponse getRoomAlarmById(long id);
      
      List<RoomAlarmResponse> getRoomAlarmByBookingId(long booking_id);

      RoomAlarmResponse saveRoomAlarm(RoomAlarm roomAlarm);
      
      CustomResponseObject updateRoomAlarm(RoomAlarm roomAlarm);
      
      CustomResponseObject deleteRoomAlarm(long id);
}
