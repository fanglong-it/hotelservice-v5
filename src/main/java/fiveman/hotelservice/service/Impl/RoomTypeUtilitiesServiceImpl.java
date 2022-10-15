package fiveman.hotelservice.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fiveman.hotelservice.entities.RoomTypeUtilities;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.RoomTypeUtilitiesRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.RoomTypeUtilitiesService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoomTypeUtilitiesServiceImpl implements RoomTypeUtilitiesService {

      @Autowired
      private RoomTypeUtilitiesRepository roomTypeUtilitiesRepository;

      @Override
      public List<RoomTypeUtilities> getRoomTypeUtilities() {
            log.info("START OF FIND ALL ROOM TYPE UTILITIES");
            return roomTypeUtilitiesRepository.findAll();
      }

      @Override
      public RoomTypeUtilities getRoomTypeUtilitiesById(long id) {
            log.info("START OF GET ROOM TYPE ULTILITIES BY ID");
            if (roomTypeUtilitiesRepository.existsById(id)) {
                  return roomTypeUtilitiesRepository.getRoomTypeUtilitiesById(id);
            }
            log.info("GET ROOM TYPE UTILITIES FAIL");
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(
                        HttpStatus.NOT_FOUND.toString(), "Not found RoomTypeUtilities Id = " + id));
      }

      @Override
      public CustomResponseObject addRoomTypeUtilities(RoomTypeUtilities roomTypeUtilities) {
            if (roomTypeUtilitiesRepository.existsById(roomTypeUtilities.getId())) {
                  throw new AppException(HttpStatus.ALREADY_REPORTED.value(),
                              new CustomResponseObject(Common.ADDING_FAIL, "Exist Id = " + roomTypeUtilities.getId()));
            }
            roomTypeUtilitiesRepository.save(roomTypeUtilities);
            return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding success!");
      }

      @Override
      public CustomResponseObject updateRoomTypeUtilities(RoomTypeUtilities roomTypeUtilities) {
            if (!roomTypeUtilitiesRepository.existsById(roomTypeUtilities.getId())) {
                  throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL,
                              "Not found Id = " + roomTypeUtilities.getId()));
            }
            roomTypeUtilitiesRepository.save(roomTypeUtilities);
            return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
      }

      @Override
      public CustomResponseObject deleteRoomTypeUtilities(long id) {
            log.info("START OF DELETE ROOM TYPE UTILITIES");
            if (!roomTypeUtilitiesRepository.existsById(id)) {
                  throw new AppException(HttpStatus.NOT_FOUND.value(),
                              new CustomResponseObject(Common.DELETE_FAIL, "Not found Id = " + id));
            }
            roomTypeUtilitiesRepository.deleteById(id);
            return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
      }

}
