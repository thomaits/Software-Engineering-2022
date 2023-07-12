package course_mgt_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MedianStatisticStrategy")
public class MedianStatisticStrategy extends TemplateStatisticStrategy{
	@Autowired
	public MedianStatisticStrategy(StudentRegistrationService theStudentRegistrationService) {
		super(theStudentRegistrationService);
	}
	@Override
	protected void doActualCalculation() {
		median = descriptiveStatistics.getPercentile(50);
	}
	
	@Override
	protected double returnValue() {
		return median;
	}
}
