package com.devsuperior.bds04.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.devsuperior.bds04.entities.Event;

public class EventDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "Campo requerido")
	private String name;

	@FutureOrPresent(message = "A data do evento não pode ser passada")
	private LocalDate date;
	private String url;

	@NotNull(message = "Campo requerido")
	private Long cityId;

	public EventDTO() {
	}

	public EventDTO(final Long id, final String name, final LocalDate date, final String url, final Long cityId) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.url = url;
		this.cityId = cityId;
	}

	public EventDTO(final Event entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.date = entity.getDate();
		this.url = entity.getUrl();
		this.cityId = entity.getCity().getId();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public void setDate(final LocalDate date) {
		this.date = date;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public Long getCityId() {
		return this.cityId;
	}

	public void setCityId(final Long cityId) {
		this.cityId = cityId;
	}
}
