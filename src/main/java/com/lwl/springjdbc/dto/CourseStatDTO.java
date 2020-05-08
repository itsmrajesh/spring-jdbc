package com.lwl.springjdbc.dto;

import com.lwl.springjdbc.domain.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CourseStatDTO {
	private Status status;
	private int count;
}
