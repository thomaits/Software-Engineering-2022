package course_mgt_app.dao;


import course_mgt_app.model.StudentRegistration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.properties")
public class TestStudentRegistrationDAOJpa {

    @Autowired
    StudentRegistrationDAO studentRegistrationDAO;

    @Test
    void testCourseDAOJpaImplIsNotNull() {
        Assertions.assertNotNull(studentRegistrationDAO);
    }

    @Test
    void testFindByStudentIdReturnsStudentRegistration() {
        StudentRegistration storedStudentRegistration = studentRegistrationDAO.findByStudentId(1);
        Assertions.assertNotNull(storedStudentRegistration);
        Assertions.assertEquals("Thomai", storedStudentRegistration.getStudentName());
    }

}
