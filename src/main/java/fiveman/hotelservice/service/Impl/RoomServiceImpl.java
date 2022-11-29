package fiveman.hotelservice.service.Impl;

import java.util.ArrayList;
import java.util.List;

import fiveman.hotelservice.response.RoomResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fiveman.hotelservice.entities.Room;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.BookingRepository;
import fiveman.hotelservice.repository.RoomRepository;
import fiveman.hotelservice.repository.RoomTypeRepository;
import fiveman.hotelservice.request.RoomRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.RoomAvailabilityResponse;
import fiveman.hotelservice.service.RoomService;
import fiveman.hotelservice.service.RoomTypeService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoomServiceImpl implements RoomService {

      @Autowired
      RoomRepository roomRepository;

      @Autowired
      ModelMapper modelMapper;

      @Autowired
      RoomTypeRepository roomTypeRepository;

      // private long id;
      // private String name;
      // private String roomNo;
      // private String description;
      // private String createDate;
      // private String updateDate;
      // private String createBy;
      // private String lastModifyBy;
      // private long hotel_Id;
      // private long roomType_Id;

      RoomResponse mapRoomToResponse(Room room) {
            RoomResponse roomResponse = new RoomResponse();
            roomResponse.setId(room.getId());
            roomResponse.setName(room.getName());
            roomResponse.setRoomNo(room.getRoomNo());
            roomResponse.setDescription(room.getDescription());
            roomResponse.setCreateDate(room.getCreateDate());
            roomResponse.setUpdateDate(room.getUpdateDate());
            roomResponse.setCreateBy(room.getCreateBy());
            roomResponse.setLastModifyBy(room.getLastModifyBy());
            roomResponse.setHotel_Id(room.getHotel().getId());
            roomResponse.setRoomType(room.getRoomType());
            roomResponse.getRoomType().setRooms(null);
            return roomResponse;
      }

      @Autowired
      BookingRepository bookingRepository;

      @Override
      public List<RoomResponse> getRoomWithBookingToday(String today) {
            List<Room> rooms = roomRepository.findAll();
            List<RoomResponse> roomResponses = new ArrayList<>();
            for (Room room : rooms) {
                  RoomResponse roomResponse = mapRoomToResponse(room);
                  roomResponse.setBooking(bookingRepository.getBookingByRoomIdToday(roomResponse.getId(), today));
                  roomResponses.add(roomResponse);
            }
            return roomResponses;
      }

      @Override
      public List<RoomResponse> getRooms() {
            log.info("GET ALL ROOMS");
            List<RoomResponse> roomResponses = new ArrayList<>();
            List<Room> rooms = roomRepository.findAll();
            for (Room r : rooms) {
                  roomResponses.add(mapRoomToResponse(r));
            }
            return roomResponses;
      }

      @Override
      public RoomResponse getRoom(long id) {
            log.info("START GET ROOM BY ID");
            if (!roomRepository.existsById(id)) {
                  throw new AppException(HttpStatus.NOT_FOUND.value(),
                              new CustomResponseObject(Common.GET_FAIL, "Cant found ID =" + id));
            }
            log.info("END GET ROOM BY ID");
            return mapRoomToResponse(roomRepository.getRoomById(id));
      }

      @Override
      public CustomResponseObject saveRoom(RoomRequest roomRequest) {
            log.info("START SAVE ROOM");
            Room room = modelMapper.map(roomRequest, Room.class);
            if (roomRepository.existsById(room.getId())) {
                  throw new AppException(HttpStatus.ALREADY_REPORTED.value(),
                              new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" + room.getId()));
            }
            roomRepository.save(room);
            // if (roomRequest.getImages().size() > 0) {
            // Room latestRoom = roomRepository.findTopByOrderByIdDesc();
            // for (ImageRequest img : roomRequest.getImages()) {
            // Image image = new Image();
            // image.setPictureUrl(img.getPictureUrl());
            // image.setPictureDescription(img.getPictureDescription());
            // image.setPictureType("img_room_" + latestRoom.getId());
            // }
            // }
            log.info("END SAVE ROOM");
            return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding Room Success!");
      }

      @Override
      public CustomResponseObject updateRoom(Room room) {
            log.info("START UPDATE ROOM");
            if (roomRepository.existsById(room.getId())) {
                  roomRepository.save(room);
                  log.info("END UPDATE ROOM");
                  return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update success!");
            }
            throw new AppException(HttpStatus.NOT_FOUND.value(),
                        new CustomResponseObject(Common.UPDATE_FAIL, "Not found id = " + room.getId()));
      }

      @Override
      public CustomResponseObject deleteRoom(long id) {
            if (roomRepository.existsById(id)) {
                  log.info("DELETE ROOM");
                  roomRepository.deleteById(id);
                  return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete success!");
            }
            throw new AppException(HttpStatus.NOT_FOUND.value(),
                        new CustomResponseObject(Common.DELETE_FAIL, "Not found id = " + id));
      }

      @Autowired
      RoomTypeService roomTypeService;

      @Override
      public List<Room> checkAvailabilityByRoomType(String dateCheckIn, String dateCheckout,
                  String numberOfPerson, int roomTypeId) {
            List<RoomAvailabilityResponse> checkAvailabilityResponses = roomTypeService.checkAvailability(dateCheckIn,
                        dateCheckout,
                        numberOfPerson);
            // List<RoomAvailabilityResponse> responses = new ArrayList<>();
            List<Room> rooms = new ArrayList<>();
            for (RoomAvailabilityResponse roomAvailabilityResponse : checkAvailabilityResponses) {
                  for (Room room : roomAvailabilityResponse.getRooms()) {
                        if (room.getRoomType().getId() == roomTypeId) {
                              rooms.add(room);
                        }
                  }
            }
            return rooms;
      }

}
