package com.lwl.springjdbc.data;

import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import com.lwl.springjdbc.domain.Course;

import lombok.Getter;
import lombok.Setter;



@Component
public class SeedDataUtil {

	private Yaml yaml;

	public List<Course> courseList() {
		String fileName = "seed_data.yml";
		CourseListDTO obj = getObject(fileName, CourseListDTO.class);
		return obj.getCourses();
	}

	public <T> T getObject(String fileName, Class<T> cls) {
		yaml = new Yaml(new Constructor(cls));
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
		@SuppressWarnings("unchecked")
		T obj = (T) yaml.load(inputStream);
		return obj;
	}

	

}
