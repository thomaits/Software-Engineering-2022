package course_mgt_app.service;



import course_mgt_app.service.CourseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import course_mgt_app.dao.CourseDAO;
import course_mgt_app.model.Course;
import course_mgt_app.service.CourseService;


@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
class TestCourseServiceWithMocks {

    @TestConfiguration
    static class CourseServiceImplTestContextConfiguration {

        @Primary
        @Bean
        public CourseService courseService() {
            return new CourseServiceImpl();
        }
    }

    @Autowired
    CourseService courseService;

    @MockBean
    CourseDAO courseDAO;

    @Test
    void testCourseDAOJpaImplIsNotNull() {
        Assertions.assertNotNull(courseService);
    }

    @Test
    void testFindByCourseIdReturnsCourse() {
        Mockito.when(courseDAO.findByCourseId(1)).thenReturn(new Course(1, "Software Engineering",
                "",1,"",""));
        Course storedCourse = courseService.findById(1);
        Assertions.assertNotNull(storedCourse);
        Assertions.assertEquals("Software Engineering", storedCourse.getCourseName());
    }


}
