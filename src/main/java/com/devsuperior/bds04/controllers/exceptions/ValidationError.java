package com.devsuperior.bds04.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private final List<FieldMessage> errors = new ArrayList<>();

	public List<FieldMessage> getErrors() {
		return this.errors;
	}

	public void addError(final String fieldName, final String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}
}
