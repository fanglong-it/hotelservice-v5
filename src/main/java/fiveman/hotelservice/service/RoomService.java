package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.Booking;
import fiveman.hotelservice.entities.Room;
import fiveman.hotelservice.request.RoomRequest;
import fiveman.hotelservice.response.RoomResponse;

public interface RoomService {

    List<Room> getRooms();

    Room getRoomByBooking(long booking_id);

    Room getRoomByOrderId(long order_id);

    RoomResponse getRoom(long id);

    Room saveRoom(RoomRequest roomRequest);

    Room updateRoom(Room room);

    Room deleteRoom(long id);

    List<Room> checkAvailabilityByRoomType(long Booking_Id);

    List<Booking> getBookingCheckInToday();

    List<RoomResponse> getRoomWithBooking();
    

}
