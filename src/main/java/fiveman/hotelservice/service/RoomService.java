package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.Room;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.RoomResponse;

public interface RoomService {
    List<RoomResponse> getRooms();

    RoomResponse getRoom(long id);

    CustomResponseObject saveRoom(Room room);

    CustomResponseObject updateRoom(Room room);

    CustomResponseObject deleteRoom(long id);
}