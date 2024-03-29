package fiveman.hotelservice.controller;

import fiveman.hotelservice.entities.Hotel;
import fiveman.hotelservice.request.HotelRequest;
import fiveman.hotelservice.service.HotelService;
import io.swagger.annotations.Api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "Hotel")
@RequestMapping("/api/v1")
public class HotelController {
      @Autowired
      HotelService hotelService;

      @Autowired
      ModelMapper modelMapper;

      @GetMapping("/hotels")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      public ResponseEntity<List<Hotel>> getHotels() {
            return new ResponseEntity<>(hotelService.getAllHotels(), HttpStatus.OK);
      }

      @GetMapping("/hotel/{id}")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      public ResponseEntity<Hotel> getHotel(@PathVariable("id") long id) {
            return new ResponseEntity<>(hotelService.getHotelById(id), HttpStatus.OK);
      }

      @PostMapping("/hotel")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      public ResponseEntity<Hotel> saveHotel(@RequestBody @Valid HotelRequest request) {
            Hotel hotel = modelMapper.map(request, Hotel.class);
            return new ResponseEntity<>(hotelService.saveHotel(hotel), HttpStatus.OK);
      }

      @PutMapping("/hotel")
      @PreAuthorize("isAnonymous() or isAuthenticated")
      public ResponseEntity<Hotel> updateHotel(@RequestBody HotelRequest request) {
            Hotel hotel = modelMapper.map(request, Hotel.class);
            return new ResponseEntity<>(hotelService.updateHotel(hotel), HttpStatus.OK);
      }

      @DeleteMapping("/hotel/{id}")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      public ResponseEntity<Hotel> deleteHotel(@PathVariable("id") long id) {
            return new ResponseEntity<>(hotelService.deleteHotel(id), HttpStatus.OK);
      }

}
