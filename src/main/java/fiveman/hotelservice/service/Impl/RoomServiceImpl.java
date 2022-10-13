package fiveman.hotelservice.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fiveman.hotelservice.entities.Room;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.RoomRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.RoomService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	RoomRepository roomRepository;

	@Override
	public List<Room> getRooms() {
		log.info("GET ALL ROOMS");
		return roomRepository.findAll();
	}

	@Override
	public Room getRoom(long id) {
		log.info("START GET ROOM BY ID");
		if (!roomRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Cant found ID =" + id));
        }
		log.info("END GET ROOM BY ID");
        return roomRepository.getRoomById(id);
	}

	@Override
	public CustomResponseObject saveRoom(Room room) {
		log.info("START SAVE ROOM");
		if (roomRepository.existsById(room.getId())) {
            throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" + room.getId()));
        }
		roomRepository.save(room);
		log.info("END SAVE ROOM");
        return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding Room Success!");
	}

	@Override
	public CustomResponseObject updateRoom(Room room) {
		log.info("START UPDATE ROOM");
		if(roomRepository.existsById(room.getId())){
			roomRepository.save(room);
			log.info("END UPDATE ROOM");
            return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update success!");
        }
        throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL, "Not found id = " + room.getId()));
	}

	@Override
	public CustomResponseObject deleteRoom(long id) {
		 if(roomRepository.existsById(id)){
			 log.info("DELETE ROOM");
			 roomRepository.deleteById(id);
	            return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete success!");
	        }
		 throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.DELETE_FAIL, "Not found id = " + id));
	}

}
