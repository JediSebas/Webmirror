package com.jedisebas.webmirror;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
