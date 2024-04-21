package ru.hogwarts.school.serviceTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

class StudentServiceTest {

    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService();
    }

    @Test
    void testCreateStudent() {
        Student student = new Student(0, "Иван Иванов", 20);
        Student createdStudent = studentService.createStudent(student);
        assertEquals(0, createdStudent.getId());
        assertEquals("Иван Иванов", createdStudent.getName());
        assertEquals(20, createdStudent.getAge());
    }

    @Test
    void testReadStudent() {
        Student student = new Student(0, "Иван Иванов", 22);
        studentService.createStudent(student);
        Student foundStudent = studentService.readStudent(0);
        assertEquals(student, foundStudent);
    }

    @Test
    void testUpdateStudent() {
        Student student = new Student(0, "Ваня Иванов", 21);
        studentService.createStudent(student);
        student.setName("Иван Иванов");
        Student updatedStudent = studentService.updateStudent(student);
        assertEquals("Иван Иванов", updatedStudent.getName());
    }

    @Test
    void testDeleteStudent() {
        Student student = new Student(0, "Иван Иванов", 23);
        studentService.createStudent(student);
        studentService.deleteStudent(0);
        assertNull(studentService.readStudent(0));
    }

    @Test
    void testGetStudentsSameAge() {
        studentService.createStudent(new Student(0, "Иван Иванов", 18));
        studentService.createStudent(new Student(1, "Петр Иванов", 18));
        studentService.createStudent(new Student(2, "Виктор Иванов", 19));

        Collection<Student> studentsAge18 = studentService.getStudentsSameAge(18);
        assertEquals(2, studentsAge18.size());
    }
}
