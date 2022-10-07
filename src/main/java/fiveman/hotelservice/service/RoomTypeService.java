package fiveman.hotelservice.service;
import fiveman.hotelservice.entities.RoomType;

import java.util.List;

public interface RoomTypeService {
    List<RoomType> findAllRoomType();
    RoomType getRoomType(long id);
    RoomType addRoomType(RoomType roomType);
    RoomType updateRoomType(RoomType roomType);
    String deleteRoomType(long id);
}
