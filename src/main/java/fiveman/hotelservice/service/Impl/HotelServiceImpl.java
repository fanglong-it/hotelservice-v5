package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.Hotel;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.HotelRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.HotelService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public List<Hotel> getAllHotels() {
        log.info("START GETTING ALL HOTEL");
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(Long id) {
        log.info("START GETTING HOTEL BY ID");
        return hotelRepository.getHotelById(id);
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        if(!hotelRepository.existsById(hotel.getId())){
            log.info("START SAVING HOTEL");
            hotelRepository.save(hotel);
            return hotelRepository.findTopByOrderByIdDesc();
        }
        throw new AppException(HttpStatus.ALREADY_REPORTED.value(),
                new CustomResponseObject(Common.ADDING_FAIL,
                        "Existed id = " + hotel.getId()));
    }

    @Override
    public Hotel updateHotel(Hotel hotel) {
        if(hotelRepository.existsById(hotel.getId())){
            log.info("START UPDATE HOTEL");
            hotelRepository.save(hotel);
            return hotelRepository.getHotelById(hotel.getId());
        }
        throw new AppException(HttpStatus.NOT_FOUND.value(),
                new CustomResponseObject(Common.UPDATE_FAIL,
                        "Not found id = " + hotel.getId()));

    }

    @Override
    public Hotel deleteHotel(Long id) {
        if(hotelRepository.existsById(id)){
            log.info("START DELETE HOTEL");
            Hotel hotel = hotelRepository.getHotelById(id);
            hotel.setStatus(false);
            hotelRepository.save(hotel);
            return hotel;
        }
        throw new AppException(HttpStatus.NOT_FOUND.value(),
                new CustomResponseObject(Common.DELETE_FAIL,
                        "Not found id = " + id));
    }
}
