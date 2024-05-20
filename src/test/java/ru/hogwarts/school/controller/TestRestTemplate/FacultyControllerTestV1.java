package ru.hogwarts.school.controller.TestRestTemplate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.impl.FacultyService;
import ru.hogwarts.school.service.impl.StudentService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static ru.hogwarts.school.controller.modelTest.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTestV1 {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private StudentService studentService;

    @Autowired
    private FacultyService facultyService;

    @Test   //Получение факультета по id
    public void getFacultyInfoTest() {

        Faculty faculty = createMockFaculty();

        ResponseEntity<Faculty> getFaculty = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/faculty/" + faculty.getId(),
                Faculty.class
        );

        assertThat(getFaculty.getStatusCode()).isEqualTo(HttpStatus.OK);

        Faculty testsFaculty = getFaculty.getBody();

        assertThat(testsFaculty.getName()).isEqualTo(testsFaculty.getName());
        assertThat(testsFaculty.getColor()).isEqualTo(testsFaculty.getColor());
    }

    @Test   //Получение студентов факультета
    public void getStudentFacultyTest() {

        facultyService.createFaculty(MOCK_FACULTY);
        MOCK_FACULTY.setStudent(MOCK_STUDENTS);

        List<Student> students = testRestTemplate.exchange(
                "http://localhost:" + port + "/faculty/students/" + MOCK_FACULTY_ID,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>() {
                }
        ).getBody();

        assertNotNull(students);
    }

    @Test   //Получение всех факультетов
    public void getAllFacultiesTest() {

        createMockFaculty();

        List<Faculty> faculties = testRestTemplate.exchange(
                "http://localhost:" + port + "/faculty/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Faculty>>() {
                }
        ).getBody();

        assertFalse(faculties.isEmpty());
    }

    @Test   //Получение факультетов по цвету
    public void getByColorTest() {

        createMockFaculty();
        createMockFaculty(MOCK_FACULTY_NAME, MOCK_FACULTY_COLOR);

        List<Faculty> faculties = testRestTemplate.exchange(
                "http://localhost:" + port + "/faculty/get-by-color?color=" + MOCK_FACULTY_COLOR
                        + "&name=" + MOCK_FACULTY_NAME,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Faculty>>() {
                }
        ).getBody();

        assertTrue(faculties.size() >= 2);
    }

    @Test   //Создание факультета
    public void createFacultyTest() {

        ResponseEntity<Faculty> newFaculty = testRestTemplate.postForEntity(
                "http://localhost:" + port + "/faculty",
                MOCK_FACULTY,
                Faculty.class
        );

        assertThat(newFaculty.getStatusCode()).isEqualTo(HttpStatus.OK);

        Faculty testsFaculty = newFaculty.getBody();

        assertThat(testsFaculty.getName()).isEqualTo(MOCK_FACULTY_NAME);
        assertThat(testsFaculty.getColor()).isEqualTo(MOCK_FACULTY_COLOR);
    }

    @Test   //Обновление факультета
    public void editFacultyTest() {

        Faculty facultyTest = createMockFaculty();
        facultyTest.setName(MOCK_FACULTY_NEW_NAME);

        testRestTemplate.put(
                "http://localhost:" + port + "/faculty",
                facultyTest,
                Faculty.class
        );

        ResponseEntity<Faculty> getFaculty = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/faculty/" + facultyTest.getId(),
                Faculty.class
        );

        assertThat(getFaculty.getStatusCode()).isEqualTo(HttpStatus.OK);
        Faculty faculty = getFaculty.getBody();
        assertThat(faculty.getName()).isEqualTo(facultyTest.getName());
    }

    @Test   //Удаление факультета
    public void deleteFacultyTest() {

        Faculty facultyTest = createMockFaculty();

        testRestTemplate.delete(
                "http://localhost:" + port + "/faculty/" + facultyTest.getId(),
                Faculty.class
        );

        ResponseEntity<Faculty> getFaculty = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/faculty/" + facultyTest.getId(),
                Faculty.class
        );

        assertThat(getFaculty.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    public Faculty createMockFaculty() {
        return facultyService.createFaculty(MOCK_FACULTY);
    }

    public Faculty createMockFaculty(String name, String color) {
        return facultyService.createFaculty(new Faculty(MOCK_STUDENT_ID, name, color));
    }
}
