package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.Image;
import fiveman.hotelservice.entities.Room;
import fiveman.hotelservice.entities.RoomType;
import fiveman.hotelservice.entities.RoomTypeUtilities;
import fiveman.hotelservice.entities.Utilities;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.ImageRepository;
import fiveman.hotelservice.repository.RoomTypeRepository;
import fiveman.hotelservice.repository.RoomTypeUtilitiesRepository;
import fiveman.hotelservice.repository.UtilitiesRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.RoomAvailabilityResponse;
import fiveman.hotelservice.service.RoomTypeService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RoomTypeServiceImpl implements RoomTypeService {

      @Autowired
      RoomTypeRepository roomTypeRepository;

      @Autowired
      RoomTypeUtilitiesRepository roomTypeUtilitiesRepository;

      @Autowired
      UtilitiesRepository utilitiesRepository;

      @Autowired
      ImageRepository imageRepository;

      @Autowired
      ModelMapper modelMapper;

      @Override
      public List<RoomType> findAllRoomType() {
            log.info("START OF FIND ALL ROOM TYPE");
            return roomTypeRepository.findAll();
      }

      @Override
      public RoomType getRoomType(long id) {
            log.info("START OF GET ROOM TYPE BY ID");
            if (roomTypeRepository.existsById(id)) {
                  return roomTypeRepository.getRoomTypeById(id);
            }
            log.info("GET ROOM TYPE FAIL");
            throw new AppException(HttpStatus.NOT_FOUND.value(),
                        new CustomResponseObject(HttpStatus.NOT_FOUND.toString(), "Not found RoomType Id = " + id));
      }

      @Override
      public CustomResponseObject addRoomType(RoomType roomType) {
            log.info("START OF ADD ROOM TYPE BY ID");
            if (roomTypeRepository.existsById(roomType.getId())) {
                  throw new AppException(HttpStatus.ALREADY_REPORTED.value(),
                              new CustomResponseObject(Common.ADDING_FAIL, "Exist Id = " + roomType.getId()));
            }
            roomTypeRepository.save(roomType);
            log.info("END OF ADD ROOM TYPE BY ID");
            return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding success!");
      }

      @Override
      public CustomResponseObject updateRoomType(RoomType roomType) {
            if (!roomTypeRepository.existsById(roomType.getId())) {
                  throw new AppException(HttpStatus.NOT_FOUND.value(),
                              new CustomResponseObject(Common.UPDATE_FAIL, "Not found Id = " + roomType.getId()));
            }
            roomTypeRepository.save(roomType);
            return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
      }

      @Override
      public CustomResponseObject deleteRoomType(long id) {
            log.info("START OF DELETE ROOM TYPE");
            if (!roomTypeRepository.existsById(id)) {
                  throw new AppException(HttpStatus.NOT_FOUND.value(),
                              new CustomResponseObject(Common.DELETE_FAIL, "Not found Id = " + id));
            }
            roomTypeRepository.deleteById(id);
            return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
      }

      @Override
      public List<RoomAvailabilityResponse> checkAvailability(String dateCheckIn, String dateCheckout,
                  String numberOfPerson) {
            log.info("START TO CHECK AVAILABILITY ");
            List<RoomType> listRoomType = roomTypeRepository.findAll();
            List<RoomAvailabilityResponse> listRoomAvailable = new ArrayList<RoomAvailabilityResponse>();
            if (fiveman.hotelservice.utils.Utilities.isEmptyString(numberOfPerson)) {
                  numberOfPerson = "1";
            }
            int numOfPerson = Integer.parseInt(numberOfPerson);

            for (RoomType roomType : listRoomType) {
                  List<Room> listRoom = roomType.getRooms();
                  if (roomType.getMaxOccupancy() >= numOfPerson) {
                        if (listRoom.size() > 0) {
                              List<Utilities> utilities = new ArrayList<Utilities>();
                              if (roomType.getRoomTypeUtilities().size() > 0) {
                                    for (RoomTypeUtilities roomTypeUtilities : roomType.getRoomTypeUtilities()) {
                                          utilities.add(roomTypeUtilities.getUtilities());
                                    }
                              }
                              RoomAvailabilityResponse roomAvailable = modelMapper.map(roomType,
                                          RoomAvailabilityResponse.class);
                              List<Image> images = imageRepository
                                          .getAllByPictureType("img_roomType_" + roomType.getId());
                              roomAvailable.setUtilities(utilities);
                              roomAvailable.setRooms(listRoom);
                              roomAvailable.setImages(images);
                              listRoomAvailable.add(roomAvailable);
                        }
                  }
            }
            return listRoomAvailable;
      }

}
