package com.lwl.springjdbc.data;

import java.util.List;

import com.lwl.springjdbc.domain.Course;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseListDTO {
	private List<Course> courses;
}