package course_mgt_app.service;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("StandardDeviationStatisticStrategy")
public class StandardDeviationStatisticStrategy extends TemplateStatisticStrategy{
	@Autowired
	public StandardDeviationStatisticStrategy(StudentRegistrationService theStudentRegistrationService) {
		super(theStudentRegistrationService);
	}

	@Override
	protected void doActualCalculation() {
		standardDeviation = descriptiveStatistics.getStandardDeviation();
	}
	
	@Override
	protected double returnValue() {
		return Precision.round(standardDeviation ,3);
	}
}
