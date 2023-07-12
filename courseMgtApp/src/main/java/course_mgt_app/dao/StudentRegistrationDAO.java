package course_mgt_app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import course_mgt_app.model.StudentRegistration;

@Repository
public interface StudentRegistrationDAO extends JpaRepository<StudentRegistration, Integer> {

	public StudentRegistration findByStudentId(int theId);

	public List<StudentRegistration> findByCourseId(int courseId);


	//public void save(StudentRegistration theStudentRegistration);
	
}