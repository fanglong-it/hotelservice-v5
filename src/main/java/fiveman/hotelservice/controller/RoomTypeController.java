package fiveman.hotelservice.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fiveman.hotelservice.entities.RoomPrice;
import fiveman.hotelservice.entities.RoomType;
import fiveman.hotelservice.request.RoomPriceRequest;
import fiveman.hotelservice.request.RoomTypeRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.RoomAvailabilityResponse;
import fiveman.hotelservice.service.RoomTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@Api(tags = "Room_Type")
public class RoomTypeController {

    @Autowired
    RoomTypeService roomTypeService;
    
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/roomTypes")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<List<RoomType>> getRoomTypes() {
        return new ResponseEntity<>(roomTypeService.findAllRoomType(), HttpStatus.OK);
    }

    @GetMapping("/roomType/{id}")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<RoomType> getRoomType(@PathVariable("id") long id) {
        return new ResponseEntity<>(roomTypeService.getRoomType(id), HttpStatus.OK);
    }

    @PutMapping("/roomType")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<CustomResponseObject> updateRoomType(@RequestBody RoomTypeRequest roomTypeRequest) {
          RoomType roomType = modelMapper.map(roomTypeRequest, RoomType.class);
        return new ResponseEntity<>(roomTypeService.updateRoomType(roomType), HttpStatus.OK);
    }

    @PostMapping("/roomType")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<CustomResponseObject> addRoomType(@RequestBody RoomTypeRequest roomTypeRequest) {
          RoomType roomType = modelMapper.map(roomTypeRequest, RoomType.class);
        return new ResponseEntity<>(roomTypeService.addRoomType(roomType), HttpStatus.OK);
    }


    @DeleteMapping("/roomType/{id}")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<CustomResponseObject> deleteRoomType(@PathVariable("id") long id) {
        return new ResponseEntity<>(roomTypeService.deleteRoomType(id), HttpStatus.OK);
    }

    @GetMapping("/roomType/checkAvailability")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    @ApiResponses(value = { //
                @ApiResponse(code = 400, message = "Something went wrong"), //
                @ApiResponse(code = 403, message = "Access denied"), //
                @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
    public ResponseEntity<List<RoomAvailabilityResponse>> checkAvailability(
                @RequestParam("dateCheckIn") String dateCheckIn,
                @RequestParam("dateCheckOut") String dateCheckOut,
                @RequestParam("numOfPerson") String numOfPerson) {
          
          return new ResponseEntity<>(roomTypeService.checkAvailability(dateCheckIn, dateCheckOut, numOfPerson), HttpStatus.OK);
    }

}
