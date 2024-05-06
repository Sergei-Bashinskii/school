package ru.hogwarts.school.controller.WebMvcTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.hogwarts.school.controller.modelTest.*;

@WebMvcTest(controllers = StudentController.class)
public class StudentControllerTestV2 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @SpyBean
    private StudentServiceImpl studentServiceImpl;

    @InjectMocks
    private StudentController studentController;

    private ObjectMapper mapper = new ObjectMapper();

    @Test   //Получение студента по id
    public void TestGetStudentInfo () throws Exception {

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(MOCK_STUDENT));

        mockMvc.perform(MockMvcRequestBuilders.get("/student/" + MOCK_STUDENT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(MOCK_STUDENT_NAME))
                .andExpect(jsonPath("$.age").value(MOCK_STUDENT_AGE));
    }

    @Test   //Получение факультета студента по его id
    public void TestGetFacultyByIdStudent() throws Exception {
        MOCK_STUDENT.setFaculty(MOCK_FACULTY);

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(MOCK_STUDENT));

        mockMvc.perform(MockMvcRequestBuilders.
                        get("/student/faculty/" + MOCK_STUDENT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(MOCK_FACULTY)));
    }

    @Test   //Получение всех студентов
    public void TestGetAllStudents() throws Exception {

        when(studentRepository.findAll()).thenReturn(MOCK_STUDENTS);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(MOCK_STUDENTS)));
    }

    @Test   //Получение студентов по возрасту
    public void TesGetByAge() throws Exception {

        Integer minAge = 10;
        Integer maxAge = 20;

        when(studentRepository.findByAgeBetween(anyInt(), anyInt()))
                .thenReturn(MOCK_STUDENTS);

        mockMvc.perform(MockMvcRequestBuilders.
                        get("/student/get-by-age?minAge=" + MOCK_STUDENT_AGE + "&maxAge=" + MOCK_STUDENT_AGE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(MOCK_STUDENTS)));

        mockMvc.perform(MockMvcRequestBuilders.
                        get("/student/get-by-age?minAge=" + minAge + "&maxAge=" + maxAge)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(MOCK_STUDENTS)));
    }

    @Test   //Создание студента
    public void testCreateStudent() throws Exception {

        JSONObject createTestStudent = new JSONObject();
        createTestStudent.put("name", MOCK_STUDENT_NAME);
        createTestStudent.put("age", MOCK_STUDENT_AGE);

        when(studentRepository.save(any(Student.class))).thenReturn(MOCK_STUDENT);

        mockMvc.perform(MockMvcRequestBuilders.post("/student")
                        .content(createTestStudent.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(MOCK_STUDENT_NAME))
                .andExpect(jsonPath("$.age").value(MOCK_STUDENT_AGE));
    }

    @Test
    public void testSaveStudent() throws Exception {
        final String name = "Vova";
        final int age = 12;
        final long id = 991;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name",name);
        studentObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
        ;

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
        ;
    }

    @Test   //Обновление студента
    public void testEditStudent() throws Exception{

        JSONObject editTestStudent = new JSONObject();
        editTestStudent.put("id", MOCK_STUDENT_ID);
        editTestStudent.put("name", MOCK_STUDENT_NAME);
        editTestStudent.put("age", MOCK_STUDENT_AGE);

        when(studentRepository.save(any(Student.class))).thenReturn(MOCK_STUDENT);

        mockMvc.perform(MockMvcRequestBuilders.put("/student")
                        .content(editTestStudent.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(MOCK_STUDENT_NAME))
                .andExpect(jsonPath("$.age").value(MOCK_STUDENT_AGE));
    }

    @Test   //Удаление студента
    public void testDeleteStudent() throws Exception{
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(MOCK_STUDENT));

        mockMvc.perform(MockMvcRequestBuilders.delete("/student/" + MOCK_STUDENT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
