package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.RoomType;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.RoomTypeRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.RoomTypeService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RoomTypeServiceImpl implements RoomTypeService {

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

    @Override
    public CustomResponseObject addRoomType(RoomType roomType) {
        if (roomTypeRepository.existsById(roomType.getId())) {
            throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist Id = " + roomType.getId()));
        }
        roomTypeRepository.save(roomType);
        return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding success!");
    }

    @Override
    public CustomResponseObject updateRoomType(RoomType roomType) {
        if (!roomTypeRepository.existsById(roomType.getId())) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL, "Not found Id = " + roomType.getId()));
        }
        roomTypeRepository.save(roomType);
        return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
    }

    @Override
    public CustomResponseObject deleteRoomType(long id) {
        log.info("START OF DELETE ROOM TYPE");
        if (!roomTypeRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.DELETE_FAIL, "Not found Id = " + id));
        }
        roomTypeRepository.deleteById(id);
        return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
    }
}