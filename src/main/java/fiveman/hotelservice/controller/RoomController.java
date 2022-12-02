package fiveman.hotelservice.controller;

import java.util.List;

import javax.validation.Valid;

import fiveman.hotelservice.response.RoomResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fiveman.hotelservice.entities.Booking;
import fiveman.hotelservice.entities.Room;
import fiveman.hotelservice.request.RoomRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = "Room")
@RequestMapping("/api/v1/")
public class RoomController {

      @Autowired
      private RoomService roomService;

      @Autowired
      private ModelMapper modelMapper;

      @GetMapping("/rooms")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<List<Room>> getRooms() {
            return new ResponseEntity<>(roomService.getRooms(), HttpStatus.OK);
      }

      @GetMapping("/room/{id}")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<RoomResponse> getRoom(@PathVariable("id") long id) {
            return new ResponseEntity<>(roomService.getRoom(id), HttpStatus.OK);
      }


      @PostMapping("/room")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<CustomResponseObject> saveRoom(@RequestBody RoomRequest request) {
            return new ResponseEntity<>(roomService.saveRoom(request), HttpStatus.OK);
      }

      @PutMapping("/room")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<CustomResponseObject> updateRoom(@RequestBody @Valid RoomRequest request) {
            Room room = modelMapper.map(request, Room.class);
            return new ResponseEntity<>(roomService.updateRoom(room), HttpStatus.OK);
      }

      @DeleteMapping("/room/{id}")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<CustomResponseObject> deleteRoom(@PathVariable long id) {
            return new ResponseEntity<>(roomService.deleteRoom(id), HttpStatus.OK);
      }

      @PostMapping("/room/checkAvaiblebilityAndType")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      public ResponseEntity<List<Room>> checkAvaiblebilityAndType(@RequestParam("booking_id") long booking_id){
            return new ResponseEntity<>(roomService.checkAvailabilityByRoomType(booking_id), HttpStatus.OK);
      }

      @GetMapping("/room/getRoomCheckInToday")
      public ResponseEntity<List<Booking>> getRoomCheckInToday(){
            return new ResponseEntity<>(roomService.getBookingCheckInToday(), HttpStatus.OK);
      }
      @GetMapping("/room/getRoomWithBooking")
      public ResponseEntity<List<RoomResponse>> getRoomWithBooking(){
            return new ResponseEntity<>(roomService.getRoomWithBooking(), HttpStatus.OK);
      }
}
