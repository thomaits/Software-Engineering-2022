package course_mgt_app.service;

import java.util.List;

import course_mgt_app.model.StudentRegistration;

public interface StudentRegistrationService {
	
	public List<StudentRegistration> findByCourseId(int theId);
	
	public StudentRegistration findByStudentId(int studentId);
	
	public void save(StudentRegistration theStudentRegistration);
		
	public void deleteById(int theId);
	
	
}
