package fiveman.hotelservice.controller;


import fiveman.hotelservice.entities.Hotel;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.HotelService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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

    @GetMapping("/hotels")
    @PreAuthorize("isAnonymous() or hasRole('ROLE_USER')")
    public ResponseEntity<List<Hotel>> getHotels(){
            return new ResponseEntity<>(hotelService.getAllHotels(), HttpStatus.OK);
    }


    @GetMapping("/hotel/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Hotel> getHotel(@PathVariable("id") long id){
        return new ResponseEntity<>(hotelService.getHotelById(id), HttpStatus.OK);
    }

    @PostMapping("/hotel")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<CustomResponseObject> saveHotel(@RequestBody @Valid Hotel hotel){
        return new ResponseEntity<>(hotelService.saveHotel(hotel), HttpStatus.OK);
    }





}
