package com.lwl.springjdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lwl.springjdbc.dao.CourseDao;
import com.lwl.springjdbc.domain.Course;
import com.lwl.springjdbc.domain.Status;

@SpringBootApplication
public class SpringjdbcApplication implements CommandLineRunner {

	@Autowired
	private CourseDao courseDao;

	public static void main(String[] args) {
		SpringApplication.run(SpringjdbcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Course c1 = new Course(2, "LWL CJ", 10, Status.COMPLETED);
		Course c2 = new Course(2, "LWL ANGULAR", 15, Status.REG_OPENED);
		Course c3 = new Course(2, "LWL REACT", 10, Status.REG_OPENED);
		Course c4 = new Course(2, "LWL SPRING REST", 20, Status.ONGOING);
		Course c5 = new Course(2, "LWL AWS", 10, Status.REG_OPENED);

		List<Course> courseList = new ArrayList<>();
		courseList.add(c2);
		courseList.add(c3);
		courseList.add(c4);
		courseList.add(c5);

		courseDao.addCourses(courseList);

	}

}
