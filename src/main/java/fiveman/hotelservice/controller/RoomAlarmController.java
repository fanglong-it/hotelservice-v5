package fiveman.hotelservice.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fiveman.hotelservice.entities.RoomAlarm;
import fiveman.hotelservice.request.RoomAlarmRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.RoomAlarmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@Api(tags = "room_alarm")
@RequestMapping("/api/v1/")
public class RoomAlarmController {
      
      @Autowired
      private RoomAlarmService roomAlarmService;
      
      @Autowired
      private ModelMapper modelMapper;
      
      @GetMapping("/roomAlarms")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<List<RoomAlarm>> getRoomAlarms() {
            return new ResponseEntity<List<RoomAlarm>>(roomAlarmService.getAllRoomAlarm(), HttpStatus.OK);
      }

      @GetMapping("/roomAlarm/{id}")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<RoomAlarm> getRoomAlarm(@PathVariable("id") long id) {
            return new ResponseEntity<RoomAlarm>(roomAlarmService.getRoomAlarm(id), HttpStatus.OK);
      }

      @PostMapping("/roomAlarm")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<CustomResponseObject> saveRoomAlarm(@RequestBody @Valid RoomAlarmRequest request) {
            RoomAlarm roomAlarm = modelMapper.map(request, RoomAlarm.class);
            return new ResponseEntity<>(roomAlarmService.saveRoomAlarm(roomAlarm), HttpStatus.OK);
      }

      @PutMapping("/roomAlarm")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<CustomResponseObject> updateroomAlarm(@RequestBody @Valid RoomAlarmRequest request) {
            RoomAlarm roomAlarm = modelMapper.map(request, RoomAlarm.class);
            return new ResponseEntity<>(roomAlarmService.updateRoomAlarm(roomAlarm), HttpStatus.OK);
      }

      @DeleteMapping("/roomAlarm/{id}")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<CustomResponseObject> deleteroomAlarm(@PathVariable long id) {
            return new ResponseEntity<>(roomAlarmService.deleteRoomAlarm(id), HttpStatus.OK);
      }
}
