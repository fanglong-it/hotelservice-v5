package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.Event;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.EventRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.EventService;
import fiveman.hotelservice.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository eventRepository;

    @Override
    public Event getEventById(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Cant found ID =" + id));
        }
        return eventRepository.getEventById(id);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public CustomResponseObject saveEvent(Event event) {
        if (eventRepository.existsById(event.getId())) {
            throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" + event.getId()));
        }
        eventRepository.save(event);
        return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding Event Success!");
    }


}
