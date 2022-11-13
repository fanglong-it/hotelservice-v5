package fiveman.hotelservice.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import fiveman.hotelservice.entities.RoomPrice;
import fiveman.hotelservice.request.RoomPriceRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.RoomPriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Room_Price")
public class RoomPriceController {

      @Autowired
      RoomPriceService roomPriceService;
      
      @Autowired
      ModelMapper modelMapper;

      @GetMapping("/roomPrice")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = {//
              @ApiResponse(code = 400, message = "Something went wrong"), //
              @ApiResponse(code = 403, message = "Access denied"), //
              @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
      public ResponseEntity<List<RoomPrice>> getroomPrices() {
          return new ResponseEntity<>(roomPriceService.getRoomPrices(), HttpStatus.OK);
      }

      @GetMapping("/roomPrice/{id}")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = {//
              @ApiResponse(code = 400, message = "Something went wrong"), //
              @ApiResponse(code = 403, message = "Access denied"), //
              @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
      public ResponseEntity<RoomPrice> getroomPrice(@PathVariable("id") long id) {
          return new ResponseEntity<>(roomPriceService.getRoomPrice(id), HttpStatus.OK);
      }

      @PutMapping("/roomPrice")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = {//
              @ApiResponse(code = 400, message = "Something went wrong"), //
              @ApiResponse(code = 403, message = "Access denied"), //
              @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
      public ResponseEntity<CustomResponseObject> updateRoomPrice(@RequestBody RoomPrice roomPrice) {
          return new ResponseEntity<>(roomPriceService.updateRoomPrice(roomPrice), HttpStatus.OK);
      }

      @PostMapping("/roomPrice")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = {//
              @ApiResponse(code = 400, message = "Something went wrong"), //
              @ApiResponse(code = 403, message = "Access denied"), //
              @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
      public ResponseEntity<CustomResponseObject> addRoomPrice(@RequestBody RoomPriceRequest roomPriceRequest) {
          RoomPrice roomPrice = modelMapper.map(roomPriceRequest, RoomPrice.class);
          return new ResponseEntity<>(roomPriceService.saveRoomPrice(roomPrice), HttpStatus.OK);
      }


      @DeleteMapping("/roomPrice/{id}")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = {//
              @ApiResponse(code = 400, message = "Something went wrong"), //
              @ApiResponse(code = 403, message = "Access denied"), //
              @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
      public ResponseEntity<CustomResponseObject> deleteRoomPrice(@PathVariable("id") long id) {
          return new ResponseEntity<>(roomPriceService.deleteRoomPrice(id), HttpStatus.OK);
      }
}
