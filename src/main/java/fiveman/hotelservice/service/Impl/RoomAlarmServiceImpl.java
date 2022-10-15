package fiveman.hotelservice.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fiveman.hotelservice.entities.RoomAlarm;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.RoomAlarmRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.RoomAlarmService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoomAlarmServiceImpl implements RoomAlarmService{
      
      @Autowired
      private RoomAlarmRepository roomAlarmRepository;
      
      @Override
      public List<RoomAlarm> getAllRoomAlarm() {
            log.info("GET ALL ROOM ALARMS");
            return roomAlarmRepository.findAll();
      }

      @Override
      public RoomAlarm getRoomAlarm(long id) {
            log.info("START GET ROOM ALARM BY ID");
            if (!roomAlarmRepository.existsById(id)) {
                throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Cant found ID =" + id));
            }
            log.info("END GET ROOM ALARM BY ID");
            return roomAlarmRepository.getRoomAlarmById(id);
      }

      @Override
      public CustomResponseObject saveRoomAlarm(RoomAlarm roomAlarm) {
            log.info("START SAVE ROOM ALARM");
            if (roomAlarmRepository.existsById(roomAlarm.getId())) {
                throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" +roomAlarm.getId()));
            }
            roomAlarmRepository.save(roomAlarm);
            log.info("END SAVE ROOM ALARM");
            return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding ROOM ALARM Success!");
      }

      @Override
      public CustomResponseObject updateRoomAlarm(RoomAlarm roomAlarm) {
            log.info("START UPDATE ROOM ALARM");
            if (roomAlarmRepository.existsById(roomAlarm.getId())) {
                roomAlarmRepository.save(roomAlarm);
                log.info("END UPDATE ROOM ALARM");
                return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update success!");
            }
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL, "Not found id = " + roomAlarm.getId()));
      }

      @Override
      public CustomResponseObject deleteRoomAlarm(long id) {
            if (roomAlarmRepository.existsById(id)) {
                  log.info("DELETE ROOM ALARM");
                  roomAlarmRepository.deleteById(id);
                  return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete success!");
              }
              throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.DELETE_FAIL, "Not found id = " + id));
      }

}
