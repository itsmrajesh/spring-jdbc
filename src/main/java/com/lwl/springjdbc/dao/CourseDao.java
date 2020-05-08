package com.lwl.springjdbc.dao;

import java.util.List;

import com.lwl.springjdbc.domain.Course;
import com.lwl.springjdbc.dto.CourseStatDTO;

public interface CourseDao {

	public int addCourse(Course course);

	public List<Course> courseList();

	public int updateCourse(Course course);

	public Course getCourseById(int cid);

	public int deleteCourse(int cid);

	public List<Course> search(String str);

	public int addCourses(List<Course> courseList);

	public List<CourseStatDTO> getCourseByStatus();

}
