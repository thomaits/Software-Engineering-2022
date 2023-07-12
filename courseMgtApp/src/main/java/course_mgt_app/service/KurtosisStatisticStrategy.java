package course_mgt_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("KurtosisStrategyService")
public class KurtosisStatisticStrategy extends TemplateStatisticStrategy {

	@Autowired
	public KurtosisStatisticStrategy(StudentRegistrationService theStudentRegistrationService) {
		super(theStudentRegistrationService);
	}
	@Override
	protected void doActualCalculation() {
		kurtosis = descriptiveStatistics.getKurtosis();
	}
	@Override
	protected double returnValue() {
		return kurtosis;
	}
}
