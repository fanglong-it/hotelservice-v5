package fiveman.hotelservice.controller;

import java.util.List;

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

import fiveman.hotelservice.entities.RoomTypeUtilities;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.RoomTypeUtilitiesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Room_Type_Utilities")
public class RoomTypeUtilitiesController {

      @Autowired
      private RoomTypeUtilitiesService roomTypeUtilitiesService;
      
      @GetMapping("/getAllRoomTypeUtilities")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = {//
              @ApiResponse(code = 400, message = "Something went wrong"), //
              @ApiResponse(code = 403, message = "Access denied"), //
              @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
      public ResponseEntity<List<RoomTypeUtilities>> getRoomTypeUtilities() {
          return new ResponseEntity<>(roomTypeUtilitiesService.getRoomTypeUtilities(), HttpStatus.OK);
      }

      @GetMapping("/roomTypeUtilities/{id}")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = {//
              @ApiResponse(code = 400, message = "Something went wrong"), //
              @ApiResponse(code = 403, message = "Access denied"), //
              @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
      public ResponseEntity<RoomTypeUtilities> getRoomTypeUtilitiesById(@PathVariable("id") long id) {
          return new ResponseEntity<>(roomTypeUtilitiesService.getRoomTypeUtilitiesById(id), HttpStatus.OK);
      }

      @PutMapping("/roomTypeUtilities")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = {//
              @ApiResponse(code = 400, message = "Something went wrong"), //
              @ApiResponse(code = 403, message = "Access denied"), //
              @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
      public ResponseEntity<CustomResponseObject> updateRoomTypeUtilities(@RequestBody RoomTypeUtilities roomTypeUtilities) {
          return new ResponseEntity<>(roomTypeUtilitiesService.updateRoomTypeUtilities(roomTypeUtilities), HttpStatus.OK);
      }

      @PostMapping("/roomTypeUtilities")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = {//
              @ApiResponse(code = 400, message = "Something went wrong"), //
              @ApiResponse(code = 403, message = "Access denied"), //
              @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
      public ResponseEntity<CustomResponseObject> addRoomTypeUtilities(@RequestBody RoomTypeUtilities roomTypeUtilities) {
          return new ResponseEntity<>(roomTypeUtilitiesService.addRoomTypeUtilities(roomTypeUtilities), HttpStatus.OK);
      }


      @DeleteMapping("/roomTypeUtilities/{id}")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = {//
              @ApiResponse(code = 400, message = "Something went wrong"), //
              @ApiResponse(code = 403, message = "Access denied"), //
              @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
      public ResponseEntity<CustomResponseObject> deleteRoomTypeUtilities(@PathVariable("id") long id) {
          return new ResponseEntity<>(roomTypeUtilitiesService.deleteRoomTypeUtilities(id), HttpStatus.OK);
      }

}
