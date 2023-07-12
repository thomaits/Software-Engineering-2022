package course_mgt_app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import course_mgt_app.model.Course;

@Repository
public interface CourseDAO extends JpaRepository<Course, Integer> {
	
	public Course findByCourseId(int courseId);

	public List<Course> findByInstructorLogin(String instructorLogin);
	
}