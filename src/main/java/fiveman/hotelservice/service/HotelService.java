package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.Hotel;
import fiveman.hotelservice.response.CustomResponseObject;

import java.util.List;

public interface HotelService {
    List<Hotel> getAllHotels();
    Hotel getHotelById(Long id);
    CustomResponseObject saveHotel(Hotel hotel);
    CustomResponseObject updateHotel(Hotel hotel);
    CustomResponseObject deleteHotel(Long id);
}
