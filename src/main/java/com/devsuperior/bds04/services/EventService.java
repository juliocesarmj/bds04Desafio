package com.devsuperior.bds04.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private CityRepository cityRepository;

	@Transactional(readOnly = true)
	public Page<EventDTO> allEventsPaged(final Pageable pageable) {
		final Page<Event> pageEvents = this.eventRepository.findAll(pageable);
		return pageEvents.map(EventDTO::new);
	}

	@Transactional
	public EventDTO insertEvent(final EventDTO dto) {
		final Event event = new Event();
		event.setName(dto.getName());
		event.setDate(dto.getDate());
		event.setUrl(dto.getUrl());
		event.setCity(this.cityRepository.getOne(dto.getCityId()));
		this.eventRepository.save(event);
		return new EventDTO(event);
	}

}
