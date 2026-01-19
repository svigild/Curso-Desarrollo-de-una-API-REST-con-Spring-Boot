package com.openwebinars.todo_rest.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record EditTaskCommand(
		String title,
		String description,
		@JsonFormat(pattern = "yyyy-MM-dd'T':HH:mm:ss")
		LocalDateTime deadLine
		) {
	
}