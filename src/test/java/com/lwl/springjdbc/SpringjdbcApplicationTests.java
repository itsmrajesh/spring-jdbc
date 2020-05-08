package com.lwl.springjdbc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.jdbc.Sql;

import com.lwl.springjdbc.dao.CourseDao;
import com.lwl.springjdbc.domain.Course;

@SpringBootTest
@Sql("classpath:script.sql")
@Profile("test")
class SpringjdbcApplicationTests {

	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	void getCourses() {
		List<Course> list = courseDao.courseList();
		System.out.println(list);
	}

	@Test
	void batchUpdate() {
		String sql = "SELECT * FROM COURSE";
		jdbcTemplate.queryForObject(sql,new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				System.out.println(rs.getString("title"));
				return null;
			}
		} );
		
	}

	void insertTest() {

	}

}
