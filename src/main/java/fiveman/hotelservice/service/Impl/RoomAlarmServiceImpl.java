package fiveman.hotelservice.service.Impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fiveman.hotelservice.entities.RoomAlarm;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.RoomAlarmRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.RoomAlarmResponse;
import fiveman.hotelservice.service.RoomAlarmService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoomAlarmServiceImpl implements RoomAlarmService {

      @Autowired
      private RoomAlarmRepository roomAlarmRepository;

      @Autowired
      ModelMapper modelMapper;

      public RoomAlarmResponse mapRoomAlarmToResponse(RoomAlarm roomAlarm) {
            RoomAlarmResponse roomAlarmResponse = modelMapper.map(roomAlarm, RoomAlarmResponse.class);
            roomAlarmResponse.setBooking_id(roomAlarm.getBooking().getId());
            return roomAlarmResponse;
      }

      @Override
      public List<RoomAlarm> getRoomAlarmByBookingId(long booking_id) {
            List<RoomAlarm> roomAlarms = roomAlarmRepository.getAllRoomAlarmByBooking_Id(booking_id);
            // List<RoomAlarmResponse> roomAlarmResponses = new ArrayList<>();
            // for (RoomAlarm roomAlarm : roomAlarms) {
            // RoomAlarmResponse roomAlarmResponse = mapRoomAlarmToResponse(roomAlarm);
            // roomAlarmResponses.add(roomAlarmResponse);
            // }

            // return
            // mapRoomAlarmToResponse(roomAlarmRepository.getRoomAlarmByBooking_Id(booking_id));
            return roomAlarms;
      }

      @Override
      public List<RoomAlarm> getAllRoomAlarm() {
            log.info("GET ALL ROOM ALARMS");

            List<RoomAlarm> roomAlarms = roomAlarmRepository.findAll();
            // List<RoomAlarmResponse> roomAlarmResponses = new ArrayList<>();
            // for (RoomAlarm roomAlarm : roomAlarms) {
            // RoomAlarmResponse roomAlarmResponse = mapRoomAlarmToResponse(roomAlarm);
            // roomAlarmResponses.add(roomAlarmResponse);
            // }
            return roomAlarms;
      }

      @Override
      public RoomAlarmResponse getRoomAlarmById(long id) {
            log.info("START GET ROOM ALARM BY ID");
            if (!roomAlarmRepository.existsById(id)) {
                  throw new AppException(HttpStatus.NOT_FOUND.value(),
                              new CustomResponseObject(Common.GET_FAIL, "Cant found ID =" + id));
            }
            log.info("END GET ROOM ALARM BY ID");
            return mapRoomAlarmToResponse(roomAlarmRepository.getRoomAlarmById(id));
      }

      @Override
      public RoomAlarm saveRoomAlarm(RoomAlarm roomAlarm) {
            log.info("START SAVE ROOM ALARM");
            if (roomAlarmRepository.existsById(roomAlarm.getId())) {
                  throw new AppException(HttpStatus.ALREADY_REPORTED.value(),
                              new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" + roomAlarm.getId()));
            }
            roomAlarmRepository.save(roomAlarm);
            log.info("END SAVE ROOM ALARM");
            // return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding ROOM ALARM
            // Success!");
            // return mapRoomAlarmToResponse(roomAlarmRepository.findTopByOrderByIdDesc());
            return roomAlarmRepository.findTopByOrderByIdDesc();
      }

      @Override
      public RoomAlarm updateRoomAlarm(RoomAlarm roomAlarm) {
            log.info("START UPDATE ROOM ALARM");
            if (roomAlarmRepository.existsById(roomAlarm.getId())) {
                  roomAlarmRepository.save(roomAlarm);
                  log.info("END UPDATE ROOM ALARM");
                  // return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update success!");
                  return roomAlarmRepository.getRoomAlarmById(roomAlarm.getId());
            }
            throw new AppException(HttpStatus.NOT_FOUND.value(),
                        new CustomResponseObject(Common.UPDATE_FAIL, "Not found id = " + roomAlarm.getId()));
      }

      @Override
      public RoomAlarm deleteRoomAlarm(long id) {
            if (roomAlarmRepository.existsById(id)) {
                  log.info("DELETE ROOM ALARM");
                  // roomAlarmRepository.deleteById(id);
                  RoomAlarm roomAlarm = roomAlarmRepository.getRoomAlarmById(id);
                  roomAlarm.setStatus(false);
                  roomAlarmRepository.save(roomAlarm);
                  // return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete success!");
                  return roomAlarm;
            }
            throw new AppException(HttpStatus.NOT_FOUND.value(),
                        new CustomResponseObject(Common.DELETE_FAIL, "Not found id = " + id));
      }

}
