package course_mgt_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import course_mgt_app.model.Course;
import course_mgt_app.model.StudentRegistration;
import course_mgt_app.service.CourseService;
import course_mgt_app.service.StudentRegistrationService;

@Controller
@RequestMapping(path = "/studentregistrations")
public class StudentRegistrationController {
	
	@Autowired
	private StudentRegistrationService studentRegistrationService;
	private CourseService courseService;
	
	@Autowired
	public StudentRegistrationController(StudentRegistrationService studentRegistrationService, CourseService courseService) {
		this.studentRegistrationService = studentRegistrationService;
		this.courseService = courseService;
	}

	// add mapping for "/list
	
	@RequestMapping("/list")
	public String listStudents(@RequestParam(name="courseId", required=false)int theId, 
			Model theModel) {
		
		// get students from db
		List<StudentRegistration> theStudentRegistrations = studentRegistrationService.findByCourseId(theId);
		Course theCourse = courseService.findById(theId);
		theModel.addAttribute("courseName", theCourse.getCourseName());
		theModel.addAttribute("studentregistrations", theStudentRegistrations);
		theModel.addAttribute("courseId", theId); // add the courseId to be used in showFormForAdd (save)
		
		return "studentregistrations/list-students";
		
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(@RequestParam("courseId")int theCourseId, Model theModel) {
		
		// create model attribute to bind from data
		StudentRegistration theStudentRegistration = new StudentRegistration(theCourseId);
		Course theCourse = courseService.findById(theCourseId);
		theModel.addAttribute("courseName", theCourse.getCourseName());
		theModel.addAttribute("studentregistration", theStudentRegistration);
		theModel.addAttribute("courseId", theCourseId);
		return "studentregistrations/student-form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId,
									Model theModel) {
		
		// get the student from the service
		StudentRegistration theStudentRegistration = studentRegistrationService.findByStudentId(theId);
		Course theCourse = courseService.findById(theStudentRegistration.getCourseId());
		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("studentregistration", theStudentRegistration);
		theModel.addAttribute("courseId", theStudentRegistration.getCourseId());
		theModel.addAttribute("courseName", theCourse.getCourseName());

		// send over to our form
		return "studentregistrations/student-form";
	}
	
	@RequestMapping("/save")
	public String saveStudent (@ModelAttribute("studentregistration") StudentRegistration theStudentRegistration, 
			Model theModel, RedirectAttributes redirectAttrs){//StudentRegistration theStudentRegistration) {
		
		// Check the field values:
		
		if (theStudentRegistration.getStudentCourseGrade() < 0.0) {
			theStudentRegistration.setStudentCourseGrade(0.0);
		}else if (theStudentRegistration.getStudentCourseGrade() > 10.0) {
			theStudentRegistration.setStudentCourseGrade(10.0);
		}
		
		if (theStudentRegistration.getStudentProjectGrade() < 0.0) {
			theStudentRegistration.setStudentProjectGrade(0.0);
		}else if (theStudentRegistration.getStudentProjectGrade() > 10.0) {
			theStudentRegistration.setStudentProjectGrade(10.0);
		}
		
		if (theStudentRegistration.getStudentName() == "") {
			theStudentRegistration.setStudentName("Student Name");
		}
		
		
		//add courseId as attribute to /list
		// NOTE: theModel.addAttribute: doesn't work for /list!!
		redirectAttrs.addAttribute("courseId", theStudentRegistration.getCourseId());
		
		// save the student
		studentRegistrationService.save(theStudentRegistration);
		
		// redirect to /students/list ACTION
		
		
		return "redirect:/studentregistrations/list"; //ERROR HERE!!
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int theId, RedirectAttributes redirectAttrs) {
		
		// delete the student
		StudentRegistration theStudentRegistration = studentRegistrationService.findByStudentId(theId);
		redirectAttrs.addAttribute("courseId", theStudentRegistration.getCourseId());
		studentRegistrationService.deleteById(theId);
		
		// redirect to /students/list ACTION
		return "redirect:/studentregistrations/list"; //ERROR HERE!!
		
	}
	
	@RequestMapping("/showFormForGrade")
	public String showFormForGrade(@RequestParam("studentId") int theStudentId, /*@RequestParam("courseId") int theCourseId,*/
			Model theModel) {

		StudentRegistration theStudentRegistration = studentRegistrationService.findByStudentId(theStudentId);
		Course theCourse = courseService.findById(theStudentRegistration.getCourseId());
		theModel.addAttribute("studentregistration", theStudentRegistration);
		theModel.addAttribute("courseId", theStudentRegistration.getCourseId());
		theModel.addAttribute(("courseName"), theCourse.getCourseName());
		return "studentregistrations/grade-form";
		// send over to our form
	}
}