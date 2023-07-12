package course_mgt_app.service;

import course_mgt_app.model.Course;

public interface StatisticStrategy {

	public abstract double calculateStatistic(Course course);
	
}
