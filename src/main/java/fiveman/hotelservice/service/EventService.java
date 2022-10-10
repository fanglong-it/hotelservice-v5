package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.Event;
import fiveman.hotelservice.response.CustomResponseObject;

import java.util.List;

public interface EventService {

    Event getEventById(Long id);

    List<Event> getAllEvents();

    CustomResponseObject saveEvent(Event event);

}
