package ru.hogwarts.school.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student;

    @BeforeEach
    public void setUp() {
        student = new Student();
        student.setId(1L);
        student.setAge(15);
        student.setName("Harry Potter");
    }

    @Test
    public void testCreateStudent() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student created = studentService.createStudent(student);
        assertNotNull(created);
        verify(studentRepository).save(student);
    }

    @Test
    public void testReadStudent() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student found = studentService.readStudent(1L);
        assertNotNull(found);
        assertEquals("Harry Potter", found.getName());
    }

    @Test
    public void testUpdateStudent() {
        when(studentRepository.save(student)).thenReturn(student);

        Student updated = studentService.updateStudent(student);
        assertNotNull(updated);
        verify(studentRepository).save(student);
    }

    @Test
    public void testDeleteStudent() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        doNothing().when(studentRepository).deleteById(1L);

        Student deleted = studentService.deleteStudent(1L);
        assertNotNull(deleted);
        verify(studentRepository).deleteById(1L);
    }

    @Test
    public void testGetAllStudents() {
        List<Student> students = new ArrayList<>();
        students.add(student);
        when(studentRepository.findAll()).thenReturn(students);

        Collection<Student> allStudents = studentService.getAll();
        assertFalse(allStudents.isEmpty());
        assertEquals(1, allStudents.size());
    }

    @Test
    public void testGetStudentsSameAge() {
        List<Student> students = new ArrayList<>();
        students.add(student);
        when(studentRepository.findAll()).thenReturn(students);

        Collection<Student> result = studentService.getStudentsSameAge(15);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}