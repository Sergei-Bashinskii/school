/*package ru.hogwarts.school.serviceTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.Collection;

class StudentServiceImplTest {

    private StudentServiceImpl studentServiceImpl;

    @BeforeEach
    void setUp() {
        studentServiceImpl = new StudentServiceImpl();
    }

    @Test
    void testCreateStudent() {
        Student student = new Student(0, "Иван Иванов", 20);
        Student createdStudent = studentServiceImpl.createStudent(student);
        assertEquals(0, createdStudent.getId());
        assertEquals("Иван Иванов", createdStudent.getName());
        assertEquals(20, createdStudent.getAge());
    }

    @Test
    void testReadStudent() {
        Student student = new Student(0, "Иван Иванов", 22);
        studentServiceImpl.createStudent(student);
        Student foundStudent = studentServiceImpl.findStudent(0L);
        assertEquals(student, foundStudent);
    }

    @Test
    void testUpdateStudent() {
        Student student = new Student(0, "Ваня Иванов", 21);
        studentServiceImpl.createStudent(student);
        student.setName("Иван Иванов");
        Student updatedStudent = studentServiceImpl.editStudent(student);
        assertEquals("Иван Иванов", updatedStudent.getName());
    }

    @Test
    void testDeleteStudent() {
        Student student = new Student(0, "Иван Иванов", 23);
        studentServiceImpl.createStudent(student);
        studentServiceImpl.deleteStudent(0L);
        assertNull(studentServiceImpl.findStudent(0L));
    }

    @Test
    void testGetStudentsSameAge() {
        studentServiceImpl.createStudent(new Student(0, "Иван Иванов", 18));
        studentServiceImpl.createStudent(new Student(1, "Петр Иванов", 18));
        studentServiceImpl.createStudent(new Student(2, "Виктор Иванов", 19));

        Collection<Student> studentsAge18 = studentServiceImpl.getAllStudents();
        assertEquals(2, studentsAge18.size());
    }
}*/
