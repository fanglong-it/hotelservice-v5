package fiveman.hotelservice.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fiveman.hotelservice.entities.Room;
import fiveman.hotelservice.entities.RoomPrice;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.RoomPriceRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.RoomResponse;
import fiveman.hotelservice.service.RoomPriceService;
import fiveman.hotelservice.service.RoomService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoomPriceServiceImpl implements RoomPriceService {
      
      @Autowired
      private RoomPriceRepository roomPriceRepository;
      
      @Override
      public List<RoomPrice> getRoomPrices() {
            log.info("GET ALL ROOM PRICE");
            return roomPriceRepository.findAll();
      }

      @Override
      public RoomPrice getRoomPrice(long id) {
            log.info("START GET ROOM PRICE BY ID");
            if (!roomPriceRepository.existsById(id)) {
                throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Cant found ID =" + id));
            }
            log.info("END GET ROOM PRICE BY ID");
            return roomPriceRepository.getRoomPriceById(id);
      }

      @Override
      public CustomResponseObject saveRoomPrice(RoomPrice roomPrice) {
            log.info("START SAVE ROOM PRICE");
            if (roomPriceRepository.existsById(roomPrice.getId())) {
                throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" + roomPrice.getId()));
            }
            roomPriceRepository.save(roomPrice);
            log.info("END SAVE ROOM PRICE");
            return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding ROOM PRICE Success!");
      }

      @Override
      public CustomResponseObject updateRoomPrice(RoomPrice roomPrice) {
            log.info("START UPDATE ROOM PRICE");
            if (roomPriceRepository.existsById(roomPrice.getId())) {
                roomPriceRepository.save(roomPrice);
                log.info("END UPDATE ROOM PRICE");
                return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update success!");
            }
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL, "Not found id = " + roomPrice.getId()));
      }

      @Override
      public CustomResponseObject deleteRoomPrice(long id) {
            if (roomPriceRepository.existsById(id)) {
                  log.info("DELETE ROOM PRICE");
                  roomPriceRepository.deleteById(id);
                  return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete success!");
              }
              throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.DELETE_FAIL, "Not found id = " + id));
      }

}
