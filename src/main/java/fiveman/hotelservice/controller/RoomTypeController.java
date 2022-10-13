package fiveman.hotelservice.controller;

import fiveman.hotelservice.entities.RoomType;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.RoomTypeService;
import fiveman.hotelservice.utils.Common;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Room_Type")
public class RoomTypeController {

    @Autowired
    RoomTypeService roomTypeService;

    @GetMapping("/roomTypes")
    @PreAuthorize("hasRole('ROLE_USER') or isAnonymous()")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<List<RoomType>> getRoomTypes() {
        return new ResponseEntity<>(roomTypeService.findAllRoomType(), HttpStatus.OK);
    }

    @GetMapping("/roomType/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or isAnonymous()")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<RoomType> getRoomType(@PathVariable("id") long id) {
        return new ResponseEntity<>(roomTypeService.getRoomType(id), HttpStatus.OK);
    }

    @PutMapping("/roomType")
    @PreAuthorize("hasRole('ROLE_USER') or isAnonymous()")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<CustomResponseObject> updateRoomType(@RequestBody RoomType roomType) {
        return new ResponseEntity<>(roomTypeService.updateRoomType(roomType), HttpStatus.OK);
    }

    @PostMapping("/roomType")
    @PreAuthorize("hasRole('ROLE_USER') or isAnonymous()")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<CustomResponseObject> addRoomType(@RequestBody RoomType roomType) {
        return new ResponseEntity<>(roomTypeService.addRoomType(roomType), HttpStatus.OK);
    }


    @DeleteMapping("/roomType/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or isAnonymous()")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<CustomResponseObject> deleteRoomType(@PathVariable("id") long id) {
        return new ResponseEntity<>(roomTypeService.deleteRoomType(id), HttpStatus.OK);
    }


}
