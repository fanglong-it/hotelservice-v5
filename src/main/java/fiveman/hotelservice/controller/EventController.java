package fiveman.hotelservice.controller;

import fiveman.hotelservice.entities.Event;
import fiveman.hotelservice.request.EventRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import springfox.documentation.annotations.ApiIgnore;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "Event")
@RequestMapping("/api/v1")
@ApiIgnore
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/event/{id}")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<Event> getEventById(@PathVariable("id") long id) {
        return new ResponseEntity<>(eventService.getEventById(id), HttpStatus.OK);
    }

    @GetMapping("/events")
    @PreAuthorize("isAnonymous() or isAuthenticated()")

    public ResponseEntity<List<Event>> getEvents() {
        return new ResponseEntity<>(eventService.getAllEvents(), HttpStatus.OK);
    }

    @PostMapping("/event")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<CustomResponseObject> saveEvent(@RequestBody @Valid EventRequest eventRequest) {
        Event event = modelMapper.map(eventRequest, Event.class);
        return new ResponseEntity<>(eventService.saveEvent(event), HttpStatus.OK);
    }

    @PutMapping("/event")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<CustomResponseObject> updateEvent(@RequestBody EventRequest eventRequest){
        Event event = modelMapper.map(eventRequest, Event.class);
        return new ResponseEntity<>(eventService.updateEvent(event), HttpStatus.OK);
    }

    @DeleteMapping("/event/{id}")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<CustomResponseObject> deleteEvent(@PathVariable("id") long id){
        return new ResponseEntity<CustomResponseObject>(eventService.deleteEvent(id), HttpStatus.OK);
    }

    

}
