package fiveman.hotelservice.service;
import fiveman.hotelservice.entities.RoomType;
import fiveman.hotelservice.response.CustomResponseObject;

import java.util.List;

public interface RoomTypeService {
    List<RoomType> findAllRoomType();

    RoomType getRoomType(long id);

    CustomResponseObject addRoomType(RoomType roomType);

    CustomResponseObject updateRoomType(RoomType roomType);

    CustomResponseObject deleteRoomType(long id);
}
