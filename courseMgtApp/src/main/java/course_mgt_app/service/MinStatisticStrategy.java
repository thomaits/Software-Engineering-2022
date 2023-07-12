package course_mgt_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MinStatisticStrategy")
public class MinStatisticStrategy extends TemplateStatisticStrategy{

	@Autowired
	public MinStatisticStrategy(StudentRegistrationService theStudentRegistrationService) {
		super(theStudentRegistrationService);
	}
	@Override
	protected void doActualCalculation() {
		min = descriptiveStatistics.getMin();
	}
	
	@Override
	protected double returnValue() {
		return min;
	}
}
	
