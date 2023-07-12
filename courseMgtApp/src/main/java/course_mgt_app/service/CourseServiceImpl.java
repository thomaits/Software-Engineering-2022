package course_mgt_app.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import course_mgt_app.dao.CourseDAO;
import course_mgt_app.model.Course;

@Service
public class CourseServiceImpl implements CourseService {

	private Map<String, Double> courseStatistics = new HashMap<String, Double>();
	private List<StatisticStrategy> calculationStrategies;
	
	// Interface classes implementation:
	
	
	/* STATISTIC COMPONENTS: */
	@Autowired
	@Qualifier("KurtosisStrategyService")
	private StatisticStrategy kurtosisStrategy;
	@Autowired
	@Qualifier("MaxStatisticStrategy")
	private StatisticStrategy maxStrategy;
	@Autowired
	@Qualifier("MinStatisticStrategy")
	private StatisticStrategy minStrategy;
	@Autowired
	@Qualifier("MeanStatisticStrategy")
	private StatisticStrategy meanStrategy;
	@Autowired
	@Qualifier("MedianStatisticStrategy")
	private StatisticStrategy medianStrategy ;
	@Autowired
	@Qualifier("PercentileStatisticStrategy")
	private StatisticStrategy percentileStrategy;
	@Autowired
	@Qualifier("SkewnessStatisticStrategy")
	private StatisticStrategy skewnessStrategy;
	@Autowired
	@Qualifier("StandardDeviationStatisticStrategy")
	private StatisticStrategy standardDeviationStrategy;
	@Autowired
	@Qualifier("VarianceStatisticStrategy")
	private StatisticStrategy varianceStrategy;
	
	/*END OF STATISTIC COMPONENTS*/
	
	
	@Autowired
	private CourseDAO courseRepository;
	
	@Autowired
	public CourseServiceImpl(CourseDAO theCourseRepository) {
		courseRepository = theCourseRepository;
	}
	

	public CourseServiceImpl() { 
		
	}
	
	@Override
	@Transactional
	public Course findById(int theId) {
		Course result = courseRepository.findByCourseId(theId);
		
		if (result != null ) {
			return result;
		}
		else {
			// we didn't find the course
			throw new RuntimeException("Did not find course id - " + theId);
		}
	}
	
	@Override
	@Transactional
	public void save(Course theCourse) {
		courseRepository.save(theCourse);
	}
	
	@Override
	@Transactional
	public void deleteById(int courseId) {
		courseRepository.deleteById(courseId);
	}
	
	@Override
	public List<Course> findByInstructorLogin(String instructorLogin){
		List<Course> result = courseRepository.findByInstructorLogin(instructorLogin);
		
		if (result != null ) {
			return result;
		}
		else {
			// we didn't find the courses
			throw new RuntimeException("Did not find courses of instructor: " + instructorLogin);
		}
	}
	

	// Statistic classes:
	
	@Override
	public Map<String, Double> getCourseStatistics(int courseId){
		
		Course course = courseRepository.findByCourseId(courseId);
		List<StatisticStrategy> myCalculationStrategies = new ArrayList<StatisticStrategy>();
		
		// add Strategies to the List:
		myCalculationStrategies.add(kurtosisStrategy);
		myCalculationStrategies.add(maxStrategy);
		myCalculationStrategies.add(minStrategy);
		myCalculationStrategies.add(meanStrategy);
		myCalculationStrategies.add(medianStrategy);
		myCalculationStrategies.add(percentileStrategy);
		myCalculationStrategies.add(skewnessStrategy);
		myCalculationStrategies.add(standardDeviationStrategy);
		myCalculationStrategies.add(varianceStrategy);
		
		// copy the list to calculationStrategies:
		this.setStatCalculationStrategies(myCalculationStrategies);
		
		// calculate statistics results:
		Double kurtosisResult = Precision.round(kurtosisStrategy.calculateStatistic(course),3);
		Double maxResult = Precision.round(maxStrategy.calculateStatistic(course),3);
		Double minResult = Precision.round(minStrategy.calculateStatistic(course),3);
		Double meanResult = Precision.round(meanStrategy.calculateStatistic(course),3);
		Double medianResult = Precision.round(medianStrategy.calculateStatistic(course),3);
		Double percentileResult = Precision.round(percentileStrategy.calculateStatistic(course),3);
		Double skewnessResult = Precision.round(skewnessStrategy.calculateStatistic(course),3);
		Double standardDeviationResult = Precision.round(standardDeviationStrategy.calculateStatistic(course),3);
		Double varianceResult = Precision.round(varianceStrategy.calculateStatistic(course),3);
		
		// put results to the Map:
		
		this.courseStatistics.put("kurtosis", kurtosisResult);
		this.courseStatistics.put("max", maxResult);
		this.courseStatistics.put("min", minResult);
		this.courseStatistics.put("mean", meanResult);
		this.courseStatistics.put("median", medianResult);
		this.courseStatistics.put("percentile", percentileResult);
		this.courseStatistics.put("skewness", skewnessResult);
		this.courseStatistics.put("standardDeviation", standardDeviationResult);
		this.courseStatistics.put("variance", varianceResult);
			
		return this.courseStatistics;
	}
	
	@Override
	public List<StatisticStrategy> getStatCalculationStrategies (){
		return this.calculationStrategies;
	}

	@Override
	public void setStatCalculationStrategies (List<StatisticStrategy> strategyList) {
		this.calculationStrategies = strategyList;
	}

}
