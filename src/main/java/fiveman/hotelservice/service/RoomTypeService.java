package fiveman.hotelservice.service;
import fiveman.hotelservice.entities.RoomPrice;
import fiveman.hotelservice.entities.RoomType;
import fiveman.hotelservice.request.RoomPriceRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.RoomAvailabilityResponse;

import java.util.List;

public interface RoomTypeService {
    List<RoomType> findAllRoomType();

    RoomType getRoomType(long id);

    CustomResponseObject addRoomType(RoomType roomType);

    CustomResponseObject updateRoomType(RoomType roomType);

    CustomResponseObject deleteRoomType(long id);
    
    List<RoomAvailabilityResponse> checkAvailability(String dateCheckIn, String dateCheckout, String numberOfPerson);
}
