package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.RoomType;
import fiveman.hotelservice.response.RoomAvailabilityResponse;

import java.util.List;

public interface RoomTypeService {
    List<RoomType> findAllRoomType();

    RoomType getRoomType(long id);

    RoomType getRoomTypeByRoomId(long room_id);

    RoomType addRoomType(RoomType roomType);

    RoomType updateRoomType(RoomType roomType);

    RoomType deleteRoomType(long id);

    List<RoomAvailabilityResponse> checkAvailability(String dateCheckIn, String dateCheckout, String numberOfPerson);
}
