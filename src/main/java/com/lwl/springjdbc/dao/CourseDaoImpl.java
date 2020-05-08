package com.lwl.springjdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lwl.springjdbc.domain.Course;
import com.lwl.springjdbc.domain.Status;
import com.lwl.springjdbc.dto.CourseStatDTO;

@Repository
public class CourseDaoImpl implements CourseDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	String addCourse = "INSERT INTO COURSE (TITLE,DURATION,STATUS) VALUES (?,?,?)";

	String getAllCourseList = "SELECT * FROM COURSE";

	String updateCourse = "UPDATE COURSE SET TITLE = ? , DURATION = ? , STATUS = ? WHERE CID = ?";

	String deleteCourse = "DELETE FROM COURSE WHERE CID = ?";

	String searchCourse = "SELECT * FROM COURSE WHERE TITLE LIKE  ?";

	String searchCourseById = "SELECT * FROM COURSE WHERE CID = ?";

	String getCourseByStatus = "SELECT COUNT(CID) as COUNT , STATUS FROM COURSE GROUP BY STATUS;";

	@Override
	public int addCourse(Course course) {
		int rows = jdbcTemplate.update(addCourse, course.getTitle(), course.getDuration(),
				course.getStatus().toString());
		return rows;
	}

	@Override
	public List<Course> courseList() {
		List<Course> list = jdbcTemplate.query(getAllCourseList, (rs, rowNum) -> courseMapper(rs));
		return list;
	}

	private Course courseMapper(ResultSet rs) throws SQLException {
		return new Course(rs.getInt("CID"), rs.getString("TITLE"), rs.getInt("DURATION"),
				Status.valueOf(rs.getString("STATUS")));
	}

	@Override
	public int updateCourse(Course course) {
		int rows = jdbcTemplate.update(updateCourse, course.getTitle(), course.getDuration(),
				course.getStatus().toString(), course.getCid());
		return rows;
	}

	@Override
	public int deleteCourse(int cid) {
		int rows = jdbcTemplate.update(deleteCourse, cid);
		return rows;
	}

	@Override
	public List<Course> search(String str) {
		List<Course> list = jdbcTemplate.query(searchCourse, new Object[] { "%" + str + "%" },
				(rs, rowNum) -> courseMapper(rs));
		return list;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Course getCourseById(int cid) {
		Course course = jdbcTemplate.queryForObject(searchCourseById, new Object[] { cid },
				(rs, rowNum) -> courseMapper(rs));
		return course;
	}

	@Override
	public int addCourses(List<Course> courseList) {
		int[] rows = jdbcTemplate.batchUpdate(addCourse, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Course course = courseList.get(i);
				ps.setString(1, course.getTitle());
				ps.setInt(2, course.getDuration());
				ps.setString(3, course.getStatus().toString());
			}

			@Override
			public int getBatchSize() {
				return courseList.size();
			}
		});
		System.out.println(Arrays.toString(rows));
		return 0;
	}

	@Override
	public List<CourseStatDTO> getCourseByStatus() {
		List<CourseStatDTO> list = jdbcTemplate.query(getCourseByStatus,
				(rs, rowNum) -> new CourseStatDTO(Status.valueOf(rs.getString("STATUS")), rs.getInt("COUNT")));
		return list;
	}

}
