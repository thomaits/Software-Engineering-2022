package course_mgt_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MeanStatisticStrategy")
public class MeanStatisticStrategy extends TemplateStatisticStrategy {
	
	@Autowired
	public MeanStatisticStrategy(StudentRegistrationService theStudentRegistrationService) {
		super(theStudentRegistrationService);
	}

	@Override
	protected void doActualCalculation() {
		mean = descriptiveStatistics.getMean();
	}
	
	@Override
	protected double returnValue() {
		return mean;
	}
}
