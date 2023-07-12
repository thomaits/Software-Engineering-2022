package course_mgt_app.service;

import course_mgt_app.model.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.properties")
public class TestCourseService {

    @Autowired
    CourseService courseService;

    @Test
    void testCourseDAOJpaImplIsNotNull(){
        Assertions.assertNotNull(courseService);
    }

    @Test
    void testFindByIdReturnsCourse(){
        Course storedCourse = courseService.findById(3);
        Assertions.assertNotNull((storedCourse));
        Assertions.assertEquals("Evolutionary Compu 2", storedCourse.getCourseName());
    }

    @Test
    void testFindByInstructorLoginReturnsInstructor(){
        Course storedCourse = courseService.findById(3);
        String instructor = storedCourse.getInstructorLogin();
        Assertions.assertNotNull(instructor);
        Assertions.assertEquals("pvassil", instructor);
    }

    @Test
    void testFindByInstructorLoginReturnsInstructorCourses(){
        Course storedCourse = courseService.findById(3);
        String instructor = storedCourse.getInstructorLogin();
        Assertions.assertNotNull(instructor);
        List<Course> storedListOfCourses = courseService.findByInstructorLogin(instructor);
        Assertions.assertEquals(3, storedListOfCourses.get(0).getCourseId());
        Assertions.assertEquals("Evolutionary Compu 2", storedListOfCourses.get(0).getCourseName());
        Assertions.assertEquals("pvassil", storedListOfCourses.get(0).getInstructorLogin());
        Assertions.assertEquals(2024, storedListOfCourses.get(0).getAcademicYear());
        Assertions.assertEquals("5", storedListOfCourses.get(0).getSemester());
        Assertions.assertEquals("Evol Algorithms", storedListOfCourses.get(0).getSyllabus());
    }

   // @Test
   // void testSave
}
