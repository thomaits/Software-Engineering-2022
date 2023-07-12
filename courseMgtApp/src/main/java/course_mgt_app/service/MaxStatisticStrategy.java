package course_mgt_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("MaxStatisticStrategy")
public class MaxStatisticStrategy extends TemplateStatisticStrategy{
	@Autowired
	public MaxStatisticStrategy(StudentRegistrationService theStudentRegistrationService) {
		super(theStudentRegistrationService);
	}
	@Override
	protected void doActualCalculation() {
		max = descriptiveStatistics.getMax();
	}
	@Override
	protected double returnValue() {
		return max;
	}
}
