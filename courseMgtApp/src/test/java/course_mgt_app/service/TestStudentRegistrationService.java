package course_mgt_app.service;


import course_mgt_app.model.StudentRegistration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.properties")
public class TestStudentRegistrationService {

    @Autowired
    StudentRegistrationService studentRegistrationService;

    @Test
    void testStudentRegistrationDAOJpaImplIsNotNull(){
        Assertions.assertNotNull(studentRegistrationService);
    }

    @Test
    void testFindByStudentIdReturnsStudentRegistration(){
        StudentRegistration storedStudentRegistration = studentRegistrationService.findByStudentId(1);
        Assertions.assertNotNull((storedStudentRegistration));
        Assertions.assertEquals("Thomai", storedStudentRegistration.getStudentName());
    }
}
