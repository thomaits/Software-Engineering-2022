package course_mgt_app.controller;


import course_mgt_app.model.Course;
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
public class TestCourseController {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CourseController courseController;

    @BeforeEach
    public void setup(){

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    }

    @Test
    void testCourseControllerIsNotNull(){
        Assertions.assertNotNull(courseController);
    }

    @Test
    void testMockMvcIsNotNull(){
        Assertions.assertNotNull(mockMvc);
    }

    @WithMockUser(value = "zarras")
    @Test
    void testListCoursesReturnsPage() throws Exception {
        mockMvc.perform(get("/courses/list")).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("courses")).
                andExpect(view().name("courses/list-courses"));
    }

    @WithMockUser(value = "zarras")
    @Test
    void testSaveCourseReturnsPage() throws Exception {

        Course aCourse = new Course("texnologia logismikou", 1, "zarras", "10", "to kalutero mathima ever");

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("courseId", Integer.toString(aCourse.getCourseId()));
        multiValueMap.add("courseName", aCourse.getCourseName());
        multiValueMap.add("academicYear", Integer.toString(aCourse.getAcademicYear()));
        multiValueMap.add("instructorLogin", aCourse.getInstructorLogin());
        multiValueMap.add("semester", aCourse.getSemester());
        multiValueMap.add("syllabus", aCourse.getSyllabus());

        mockMvc.perform(
                post("/courses/save").
                        params(multiValueMap)).
                andExpect(status().isFound()).
                andExpect(view().name("redirect:/courses/list"));

    }

}
