package com.jedisebas.webmirror;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EventService {


    public final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void addNewEvent(Event event) {
        eventRepository.save(event);
    }

    public ArrayList<Event> getEvents(Long userid) {
        return eventRepository.findEventsByUserId(userid);
    }

    public void deleteEvent(String event_name) {
        eventRepository.deleteEvent(event_name);
    }
}
