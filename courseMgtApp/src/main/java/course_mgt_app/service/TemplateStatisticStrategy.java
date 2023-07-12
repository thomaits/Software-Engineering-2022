package course_mgt_app.service;

import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import course_mgt_app.model.Course;
import course_mgt_app.model.StudentRegistration;


public abstract  class TemplateStatisticStrategy implements StatisticStrategy {
	//private double[] projectGrades; //needed?
	//private double[] courseGrades;
	
	protected double mean;
	protected double variance;
	protected double standardDeviation;
	protected double percentile;
	protected double kurtosis;
	protected double skewness;
	protected double median;
	protected double min;
	protected double max;
	
	protected DescriptiveStatistics descriptiveStatistics;
	
	private List<StudentRegistration> theStudentRegistrations;
	
	
	
	
	private StudentRegistrationService studentRegistrationService; // NULL!!


	public TemplateStatisticStrategy(StudentRegistrationService theStudentRegistrationService) {
		this.studentRegistrationService = theStudentRegistrationService;
	}

	public TemplateStatisticStrategy() {
		
	}

	
	@Override
	public double calculateStatistic(Course course) {
		prepareDataSet(course);
		doActualCalculation(); // expect mean, kyrtosis, variance, min/max.... to be calculated here
		return returnValue();
	}
	
	private void prepareDataSet(Course course) {
		// get a list of the Student Registrations of the course:
		theStudentRegistrations = studentRegistrationService.findByCourseId(course.getCourseId());
		
		// initialize a DescriptiveStatistics object:
		descriptiveStatistics = new DescriptiveStatistics();
		
		// add the grades to our DescriptiveStatistics object
		for (StudentRegistration tempRegistration : theStudentRegistrations) {
			descriptiveStatistics.addValue(tempRegistration.getStudentCourseGrade());
		}

	}
	
	protected abstract void doActualCalculation();
	protected abstract double returnValue();
	
}
