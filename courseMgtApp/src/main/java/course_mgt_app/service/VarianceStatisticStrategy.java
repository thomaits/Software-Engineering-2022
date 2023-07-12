package course_mgt_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("VarianceStatisticStrategy")

public class VarianceStatisticStrategy extends TemplateStatisticStrategy{
	@Autowired
	public VarianceStatisticStrategy(StudentRegistrationService theStudentRegistrationService) {
		super(theStudentRegistrationService);
	}
	@Override
	protected void doActualCalculation() {
		variance = descriptiveStatistics.getVariance();
	}
	
	@Override
	protected double returnValue() {
		return variance;
	}
}
