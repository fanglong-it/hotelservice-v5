package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.Room;
import fiveman.hotelservice.response.CustomResponseObject;

public interface RoomService {
	List<Room> getRooms();
	Room getRoom(long id);
	CustomResponseObject saveRoom(Room room);
	CustomResponseObject updateRoom(Room room);
	CustomResponseObject deleteRoom(long id);
}
