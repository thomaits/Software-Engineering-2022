package course_mgt_app.dao;


import course_mgt_app.model.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.properties")
public class TestCourseDAOJpa {
    @Autowired
    CourseDAO courseDAO;

    @Test
    void testCourseDAOJpaImplIsNotNull() {
        Assertions.assertNotNull(courseDAO);
    }

    @Test
    void testFindByCourseIdReturnsCourse() {
        Course storedCourse = courseDAO.findByCourseId(4);
        Assertions.assertNotNull(storedCourse);
        Assertions.assertEquals("texnologia logismikou", storedCourse.getCourseName());
    }



}
