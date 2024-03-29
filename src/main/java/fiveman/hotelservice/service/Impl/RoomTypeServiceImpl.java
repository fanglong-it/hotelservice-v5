package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.*;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.*;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.RoomAvailabilityResponse;
import fiveman.hotelservice.service.RoomTypeService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    @Autowired
    RoomRepository roomRepository;

    // @Autowired
    // private RoomPriceRepository roomPriceRepository;

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
    public RoomType addRoomType(RoomType roomType) {
        log.info("START OF ADD ROOM TYPE BY ID");
        if (roomTypeRepository.existsById(roomType.getId())) {
            throw new AppException(HttpStatus.ALREADY_REPORTED.value(),
                    new CustomResponseObject(Common.ADDING_FAIL, "Exist Id = " + roomType.getId()));
        }
        roomTypeRepository.save(roomType);
        log.info("END OF ADD ROOM TYPE BY ID");
        // return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding success!");
        return roomTypeRepository.findTopByOrderByIdDesc();
    }

    @Override
    public RoomType updateRoomType(RoomType roomType) {
        if (!roomTypeRepository.existsById(roomType.getId())) {
            throw new AppException(HttpStatus.NOT_FOUND.value(),
                    new CustomResponseObject(Common.UPDATE_FAIL, "Not found Id = " + roomType.getId()));
        }
        roomTypeRepository.save(roomType);
        // return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
        return roomTypeRepository.getRoomTypeById(roomType.getId());
    }

    @Override
    public RoomType deleteRoomType(long id) {
        log.info("START OF DELETE ROOM TYPE");
        if (!roomTypeRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(),
                    new CustomResponseObject(Common.DELETE_FAIL, "Not found Id = " + id));
        }
        // roomTypeRepository.deleteById(id);
        RoomType roomType = roomTypeRepository.getRoomTypeById(id);
        roomType.setActive(false);
        roomTypeRepository.save(roomType);
        // return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
        return roomType;
    }

    @Override
    public List<RoomAvailabilityResponse> checkAvailability(String dateCheckIn, String dateCheckout,
            String numberOfPerson) {
        log.info("START TO CHECK AVAILABILITY ");
        boolean isDateCheckInValid = fiveman.hotelservice.utils.Utilities.isDateValid(dateCheckIn);
        boolean isDateCheckoutValid = fiveman.hotelservice.utils.Utilities.isDateValid(dateCheckout);
        if (!isDateCheckInValid || !isDateCheckoutValid) {
            throw new AppException(HttpStatus.BAD_REQUEST.value(),
                    new CustomResponseObject(Common.GET_FAIL, "Invalid Date"));
        }

        List<RoomType> listRoomType = roomTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "defaultPrice"));
        List<RoomAvailabilityResponse> listRoomAvailable = new ArrayList<RoomAvailabilityResponse>();
        if (fiveman.hotelservice.utils.Utilities.isEmptyString(numberOfPerson)) {
            numberOfPerson = "1";
        }
        int numOfPerson = Integer.parseInt(numberOfPerson);

        for (RoomType roomType : listRoomType) {

            List<Room> listRoomAbstract = new ArrayList<Room>();
            List<Room> listRoom = roomType.getRooms();
            for (Room room : listRoom) {
                if (!room.isStatus()) {
                    listRoomAbstract.add(room);
                }
            }
            List<Room> listRoomByEndDate = roomRepository.getRoomByBookingEndDate(roomType.getId(),
                    dateCheckIn + " 00:00:00");

            for (Room room : listRoomByEndDate) {
                listRoomAbstract.add(room);
            }

            if (roomType.getMaxBookingRoom() > listRoomAbstract.size()) {
                roomType.setMaxBookingRoom(listRoomAbstract.size());
            }

            List<RoomPrice> listRoomPrice = roomType.getRoomPrices();
            for (RoomPrice roomPrice : listRoomPrice) {
                boolean isPriceByDate = fiveman.hotelservice.utils.Utilities.compareTwoDateString(dateCheckIn,
                        roomPrice.getDate());
                if (isPriceByDate) {
                    if (roomType.getMaxBookingRoom() > roomPrice.getMaxBookingRoom()) {
                        roomType.setMaxBookingRoom(roomPrice.getMaxBookingRoom());
                    }
                }
            }

            if (roomType.getMaxOccupancy() >= numOfPerson && roomType.getMaxBookingRoom() > 0) {
                if (listRoom.size() > 0) {
                    List<Utilities> utilities = new ArrayList<Utilities>();
                    if (roomType.getRoomTypeUtilities().size() > 0) {
                        for (RoomTypeUtilities roomTypeUtilities : roomType.getRoomTypeUtilities()) {
                            utilities.add(roomTypeUtilities.getUtilities());
                        }
                    }
                    RoomAvailabilityResponse roomAvailable = modelMapper.map(roomType, RoomAvailabilityResponse.class);
                    // List<Image> images = imageRepository.getAllByPictureType("img_roomType_" +
                    // roomType.getId());
                    roomAvailable.setUtilities(utilities);
                    roomAvailable.setRooms(listRoomAbstract);
                    // roomAvailable.setImages(images);
                    listRoomAvailable.add(roomAvailable);
                }
                roomTypeRepository.save(roomType);
            }
        }
        return listRoomAvailable;
    }

    @Override
    public RoomType getRoomTypeByRoomId(long room_id) {
        RoomType roomType = roomTypeRepository.getRoomTypeByRoomId(room_id);
        if (roomType == null) {
            throw new AppException(HttpStatus.NOT_FOUND.value(),
                    new CustomResponseObject(Common.GET_FAIL, "Can't find room type by roomId = " + room_id));
        }
        return roomType;
    }

}
