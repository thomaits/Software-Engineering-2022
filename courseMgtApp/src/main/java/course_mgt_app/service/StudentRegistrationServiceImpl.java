package course_mgt_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import course_mgt_app.dao.StudentRegistrationDAO;
import course_mgt_app.model.StudentRegistration;

@Service
public class StudentRegistrationServiceImpl implements StudentRegistrationService {

	@Autowired
	private StudentRegistrationDAO studentRegistrationRepository;
	
	@Autowired
	public StudentRegistrationServiceImpl(StudentRegistrationDAO theStudentRepository) {
		studentRegistrationRepository = theStudentRepository;
	}
	
	public StudentRegistrationServiceImpl() {
		
	}
	
	
	@Override
	@Transactional
	public List<StudentRegistration> findByCourseId(int courseId) {
		List<StudentRegistration> result = studentRegistrationRepository.findByCourseId(courseId);
				
		if (result != null ) {
			return result;
		}
		else {
			// we didn't find the student
			throw new RuntimeException("Did not find Course id - " + courseId);
		}
	}
	
	@Override
	@Transactional
	public void save(StudentRegistration theStudentRegistration) {
		studentRegistrationRepository.save(theStudentRegistration);
	}
	
	@Override
	@Transactional
	public void deleteById(int theId) {
		studentRegistrationRepository.deleteById(theId);
	}

	@Override
	public StudentRegistration findByStudentId(int studentId) {
		return studentRegistrationRepository.findByStudentId(studentId);
	}


}
