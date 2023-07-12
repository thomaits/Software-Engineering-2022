package course_mgt_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PercentileStatisticStrategy")
public class PercentileStatisticStrategy extends TemplateStatisticStrategy {

	@Autowired
	public PercentileStatisticStrategy(StudentRegistrationService theStudentRegistrationService) {
		super(theStudentRegistrationService);
	}
	@Override
	protected void doActualCalculation() {
		//TODO
		percentile = descriptiveStatistics.getPercentile(10);
	}
	
	@Override
	protected double returnValue() {
		return percentile;
	}
}
