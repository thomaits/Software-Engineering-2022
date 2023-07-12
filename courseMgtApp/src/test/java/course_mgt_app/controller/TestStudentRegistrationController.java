package course_mgt_app.controller;


import course_mgt_app.model.StudentRegistration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.properties")
@AutoConfigureMockMvc
public class TestStudentRegistrationController {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    StudentRegistrationController studentRegistrationController;

    @BeforeEach
    public void setup(){

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    }

    @Test
    void testCourseControllerIsNotNull(){
        Assertions.assertNotNull(studentRegistrationController);
    }

    @Test
    void testMockMvcIsNotNull(){
        Assertions.assertNotNull(mockMvc);
    }

    @WithMockUser(value = "zarras")
    @Test
    void testListStudentRegistrationsReturnsPage() throws Exception {
        mockMvc.perform(get("/studentregistrations/list?courseId=1")).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("studentregistrations")).
                andExpect(view().name("studentregistrations/list-students"));
    }

    @WithMockUser(value = "zarras")
    @Test
    void testSaveStudentRegistrationReturnsPage() throws Exception {

        StudentRegistration aStudentRegistration = new StudentRegistration("John",
                2013, 5, 10, 9.9,
                9.9, 1);
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("studentId", Integer.toString(aStudentRegistration.getStudentId()));
        multiValueMap.add("studentName", aStudentRegistration.getStudentName());
        multiValueMap.add("studentYearOfRegistration", Integer.toString(aStudentRegistration.getStudentYearOfRegistration()));
        multiValueMap.add("studentYearOfStudies", Integer.toString(aStudentRegistration.getStudentYearOfStudies()));
        multiValueMap.add("studentCurrentSemester", Integer.toString(aStudentRegistration.getStudentCurrentSemester()));
        multiValueMap.add("studentCourseGrade", Double.toString(aStudentRegistration.getStudentCourseGrade()));
        multiValueMap.add("studentProjectGrade", Double.toString(aStudentRegistration.getStudentProjectGrade()));
        multiValueMap.add("courseId", Integer.toString(aStudentRegistration.getCourseId()));


        mockMvc.perform(
                        post("/studentregistrations/save").
                                params(multiValueMap)).
                andExpect(status().isFound()).
                andExpect(view().name("redirect:/studentregistrations/list"));

    }

}
