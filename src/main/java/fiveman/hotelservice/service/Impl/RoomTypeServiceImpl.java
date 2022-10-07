package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.RoomType;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.RoomTypeRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.RoomTypeService;
import fiveman.hotelservice.utils.Common;
import fiveman.hotelservice.utils.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RoomTypeServiceImpl implements RoomTypeService {

    //    Logger logger = LoggerFactory.getLogger(RoomTypeServiceImpl.class);
    @Autowired
    RoomTypeRepository roomTypeRepository;

    @Override
    public List<RoomType> findAllRoomType() {
        log.info("START OF FIND ALL ROOM TYPE");
        return roomTypeRepository.findAll();
    }

    @Override
    public RoomType getRoomType(long id) {
        log.info("START OF GET ROOM BY ID");
        if (roomTypeRepository.existsById(id)) {
            return roomTypeRepository.getRoomTypeById(id);
        }
        log.info("GET ROOM TYPE FAIL");
        throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(HttpStatus.NOT_FOUND.toString(), "Not found RoomType Id = " + id));
    }

    RoomType checkRoomType(RoomType roomType) {
        if (Utilities.isEmptyString(roomType.getDescription())) {
            roomType.setDescription(Common.ROOM_TYPE_DESCRIPTION);
        }
        if (Utilities.isEmptyString(roomType.getPicture())) {
            roomType.setPicture(Common.ROOM_TYPE_IMAGE_URL);
        }
        if (Utilities.isEmptyString(roomType.getName())) {
            roomType.setName(Common.ROOM_TYPE_NAME);
        }
        return roomType;
    }

    @Override
    public RoomType addRoomType(RoomType roomType) {
        log.info("START OF ADD ROOM TYPE");
        if (!roomTypeRepository.existsById(roomType.getId())) {
            RoomType roomTypeChecked = checkRoomType(roomType);
            return roomTypeRepository.save(roomTypeChecked);
        }
        log.info("ADD ROOM TYPE FAIL");
        throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(HttpStatus.ALREADY_REPORTED.toString(), "The roomType id is Exist = " + roomType.getId()));
    }

    @Override
    public RoomType updateRoomType(RoomType roomType) {
        log.info("START OF UPDATE ROOM TYPE");
        RoomType oldRoomType = roomTypeRepository.getRoomTypeById(roomType.getId());
        if (oldRoomType != null) {
            if (Utilities.isEmptyString(roomType.getName())) {
                roomType.setName(oldRoomType.getName());
            }
            if (Utilities.isEmptyString(roomType.getPicture())) {
                roomType.setPicture(oldRoomType.getPicture());
            }
            if (Utilities.isEmptyString(roomType.getDescription())) {
                roomType.setDescription(oldRoomType.getDescription());
            }
            roomTypeRepository.save(roomType);
            log.info("END OF UPDATE ROOM TYPE");
            return roomTypeRepository.getRoomTypeById(roomType.getId());
        }
        throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(HttpStatus.NOT_FOUND.toString(), "Not found id = " + roomType.getId()));
    }

    @Override
    public String deleteRoomType(long id) {
        log.info("START OF DELETE ROOM TYPE");
        if (roomTypeRepository.existsById(id)) {
            roomTypeRepository.delete(roomTypeRepository.getRoomTypeById(id));
            return "Delete Success";
        }
        throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(HttpStatus.ALREADY_REPORTED.toString(), "Not found id =" + id));

    }
}