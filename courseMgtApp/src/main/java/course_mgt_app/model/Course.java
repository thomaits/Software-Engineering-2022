package course_mgt_app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table (name="courses")
public class Course {
	
	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column(name="courseId")
	private int courseId;
	private String instructorLogin;
	@Column(name="courseName")
	private String courseName;

   // @Column(name = "instructor_Login")
	
	
	@Column(name="academicYear")
	private int academicYear;
	
	@Column(name="semester")
	private String semester;
	
	@Column(name="syllabus")
	private String syllabus;
	

	// define Constructors:

	public Course() {
		
	}
	
	public Course(int courseId, String courseName, String instructorLogin, int academicYear, String semester,
			String syllabus) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.academicYear = academicYear;
		this.semester = semester;
		this.instructorLogin = instructorLogin;
		this.syllabus = syllabus;
	}

	public Course(String courseName, int academicYear, String instructorLogin, String semester, String syllabus) {
		super();
		this.courseName = courseName;
		this.academicYear = academicYear;
		this.semester = semester;
		this.instructorLogin = instructorLogin;
		this.syllabus = syllabus;
	}
	public Course(String instructorLogin) {
		super();
		this.instructorLogin = instructorLogin;
	}

	// Getters and Setters:
	public int getCourseId() {
		return courseId;
	}
	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getInstructorLogin() {
		return instructorLogin;
	}
	
	public void setInstructorLogin(String instructorLogin) {
		this.instructorLogin = instructorLogin;
	}
	
	public int getAcademicYear() {
		return academicYear;
	}
	
	public void setAcademicYear(int academicYear) {
		this.academicYear = academicYear;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}
	
	@Override
	public String toString() {
		return "Course [id=" + courseId + 
			   ", Course Name=" + courseName + ", InstructorLogin = " + instructorLogin + ", Academic Year=" + academicYear + ", Semester=" + semester + ", Syllabus=" + syllabus + "]";
	}

	/*
	{public ArrayList<StudentRegistration> getStudentsRegistered() {
		return studentsRegistered;
	}

	public void setStudentsRegistered(ArrayList<StudentRegistration> studentsRegistered) {
		this.studentsRegistered = studentsRegistered;
	}
	*/
}
