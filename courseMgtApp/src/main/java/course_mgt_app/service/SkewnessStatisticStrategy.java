package course_mgt_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("SkewnessStatisticStrategy")
public class SkewnessStatisticStrategy extends TemplateStatisticStrategy{
	
	@Autowired
	public SkewnessStatisticStrategy(StudentRegistrationService theStudentRegistrationService) {
		super(theStudentRegistrationService);
	}
	@Override
	protected void doActualCalculation() {
		skewness = descriptiveStatistics.getSkewness();
	}
	
	@Override
	protected double returnValue() {
		return skewness;
	}
}
