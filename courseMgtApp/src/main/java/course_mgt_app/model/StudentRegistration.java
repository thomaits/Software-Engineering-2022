package course_mgt_app.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity 
@Table(name="student_registrations")
public class StudentRegistration {
	
	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="student_Id")
	private int studentId;
	
	@Column(name="student_Name")
	private String studentName;
	
	@Column(name="student_Year_Of_Registration")
	private int studentYearOfRegistration;
	
	@Column(name="student_Year_Of_Studies")
	private int studentYearOfStudies;
	
	@Column(name="student_Current_Semester")
	private int studentCurrentSemester;
	
	@Column(name="student_Course_Grade")
	private double studentCourseGrade;
	
	@Column(name="student_Project_Grade")
	private double studentProjectGrade;

	//@JoinColumn(name = "course_Id", table = "courses")
	@Column(name="course_Id")
	private int courseId;
	


	// define constructors:
	
	public StudentRegistration() {
		
	}
	public StudentRegistration(int courseId) {
		super();
		this.courseId = courseId;
		
	}
	public StudentRegistration(int studentId, int courseId) {
		super();
		this.studentId = studentId;
		this.courseId = courseId;
		
	}
	public StudentRegistration(int studentId, String studentName, int studentYearOfRegistration, int studentYearOfStudies,
							   int studentCurrentSemester) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentYearOfRegistration = studentYearOfRegistration;
		this.studentYearOfStudies = studentYearOfStudies;
		this.studentCurrentSemester = studentCurrentSemester;
	}
	
	public StudentRegistration(String studentName, int studentYearOfRegistration, int studentYearOfStudies,
							   int studentCurrentSemester) {
		super();
		this.studentName = studentName;
		this.studentYearOfRegistration = studentYearOfRegistration;
		this.studentYearOfStudies = studentYearOfStudies;
		this.studentCurrentSemester = studentCurrentSemester;
	}

	public StudentRegistration(String studentName, int studentYearOfRegistration,
							   int studentYearOfStudies, int studentCurrentSemester,
							   double studentCourseGrade, double studentProjectGrade,
							   int courseId) {
		this.studentName = studentName;
		this.studentYearOfRegistration = studentYearOfRegistration;
		this.studentYearOfStudies = studentYearOfStudies;
		this.studentCurrentSemester = studentCurrentSemester;
		this.studentCourseGrade = studentCourseGrade;
		this.studentProjectGrade = studentProjectGrade;
		this.courseId = courseId;
	}

	// Getters & Setters:

	

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentYearOfRegistration() {
		return studentYearOfRegistration;
	}

	public void setStudentYearOfRegistration(int studentYearOfRegistration) {
		this.studentYearOfRegistration = studentYearOfRegistration;
	}

	public int getStudentYearOfStudies() {
		return studentYearOfStudies;
	}

	public void setStudentYearOfStudies(int studentYearOfStudies) {
		this.studentYearOfStudies = studentYearOfStudies;
	}

	public int getStudentCurrentSemester() {
		return studentCurrentSemester;
	}

	public void setStudentCurrentSemester(int studentCurrentSemester) {
		this.studentCurrentSemester = studentCurrentSemester;
	}
	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public int getCourseId() {
		return this.courseId;
	}
	
	public void setStudentCourseGrade(double grade) {
		this.studentCourseGrade = grade;
	}
	
	public double getStudentCourseGrade() {
		return this.studentCourseGrade;
	}
	
	public void setStudentProjectGrade(double grade) {
		this.studentProjectGrade = grade;
	}
	
	public double getStudentProjectGrade() {
		return this.studentProjectGrade;
	}
	// define toString
	

	@Override
	public String toString() {
		return "StudentRegistration [id=" + studentId +
			   ", StudentRegistration Name=" + studentName + ", StudentRegistration Year Of Registration=" + studentYearOfRegistration + ", StudentRegistration Year Of Studies=" + studentYearOfStudies + ", StudentRegistration Current Semester=" + studentCurrentSemester + "Course Grade = " + studentCourseGrade + "Project Grade = " + studentProjectGrade + "courseId = "+ courseId + "]";
	}
	
}


