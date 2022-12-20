package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.RoomAlarm;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.RoomAlarmResponse;

public interface RoomAlarmService {

      List<RoomAlarm> getAllRoomAlarm();

      RoomAlarmResponse getRoomAlarmById(long id);

      List<RoomAlarm> getRoomAlarmByBookingId(long booking_id);

      RoomAlarm saveRoomAlarm(RoomAlarm roomAlarm);

      RoomAlarm updateRoomAlarm(RoomAlarm roomAlarm);

      CustomResponseObject deleteRoomAlarm(long id);
}
