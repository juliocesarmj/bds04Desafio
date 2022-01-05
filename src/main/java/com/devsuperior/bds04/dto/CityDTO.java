package com.devsuperior.bds04.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.devsuperior.bds04.entities.City;

public class CityDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "Campo requerido")
	private String name;

	public CityDTO() {
	}

	public CityDTO(final Long id, final String name) {
		this.id = id;
		this.name = name;
	}

	public CityDTO(final City entity) {
		this.id = entity.getId();
		this.name = entity.getName();
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
}
