package course_mgt_app.service;

import java.util.List;
import java.util.Map;

import course_mgt_app.model.Course;

public interface CourseService {
	

	
	public Course findById(int theId);
	
	public List<Course> findByInstructorLogin(String instructorLogin);
	
	public void save(Course theCourse);
	
	public void deleteById(int theId);

	
	// statistics:
	public Map<String, Double> getCourseStatistics(int courseId);
	
	public List<StatisticStrategy> getStatCalculationStrategies ();
	
	public void setStatCalculationStrategies (List<StatisticStrategy> strategyList);


}
