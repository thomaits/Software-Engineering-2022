package course_mgt_app.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import course_mgt_app.model.Course;
import course_mgt_app.service.CourseService;

@Controller
@RequestMapping(path = "/courses")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	// add mapping for "/list
	
	@RequestMapping("/list")
	public String listCourses(Model theModel) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String instructorLogin = authentication.getName();	
		
		// get courses from db
		List<Course> theCourses = courseService.findByInstructorLogin(instructorLogin);
		
		if (theCourses == null) {
			// display a message
		}
		// add the spring model
		theModel.addAttribute("courses", theCourses);
		
		return "courses/list-courses";
		
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String instructorLogin = authentication.getName();	
		
		// create model attribute to bind from data
		Course theCourse = new Course(instructorLogin);
		
		theModel.addAttribute("course", theCourse);
		return "courses/course-form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("courseId") int theId,
									Model theModel) {
		
		// get the course from the service
		Course theCourse = courseService.findById(theId);
		// set course as a model attribute to pre-populate the form
		theModel.addAttribute("course", theCourse);

		
		// send over to our form
		return "courses/course-form";			
	}
	
	@RequestMapping("/save")
	public String saveCourse(@ModelAttribute("courseId") Course theCourse,
								Model theModel) {
		if (theCourse.getCourseName() == "") {
			theCourse.setCourseName("Course Name");
		}
		// save the course
		courseService.save(theCourse);
		// redirect to /courses/list ACTION
		return "redirect:/courses/list";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("courseId") int theId) {
		
		// delete course
		courseService.deleteById(theId);
		
		// redirect to /courses/list ACTION
		return "redirect:/courses/list";
		
	}
	
	@RequestMapping("/showCourseStatistics")
	public String showCourseStatistics(@RequestParam("courseId") int theCourseId, Model theModel) {

		// add the course name to the model:
		Course theCourse = courseService.findById(theCourseId);
		theModel.addAttribute("courseName", theCourse.getCourseName());
		
		// get the course's statistics:
		Map<String, Double> statMap = courseService.getCourseStatistics(theCourseId);
		
		Set<String> keySet = statMap.keySet();
		
		// add the statistics to the model:
		for (String key : keySet) {
			theModel.addAttribute(key,statMap.get(key));
		}
			
		return "courses/list-statistics";
		
	}
}