package com.devsuperior.bds04.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.services.EventService;

@RestController
@RequestMapping(value = "/events")
public class EventController {

	@Autowired
	private EventService service;

	@GetMapping
	public ResponseEntity<Page<EventDTO>> allEvents(final Pageable pageable) {
		return ResponseEntity.ok().body(this.service.allEventsPaged(pageable));
	}

	@PostMapping
	public ResponseEntity<EventDTO> insertEvent(@Valid @RequestBody final EventDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.insertEvent(dto));
	}
}
