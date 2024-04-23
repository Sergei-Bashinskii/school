package ru.hogwarts.school.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.EntityExistsException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student readStudent(long id) {
        return studentRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student deleteStudent(long id) {
        Student student = readStudent(id);
        studentRepository.deleteById(id);
        return student;
    }

    public List<Student> getStudentsByAgeRange(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }

    public Collection<Student> getStudentsSameAge(int ageStudent) {
        return getAll().stream()
                .filter(s -> s.getAge() == ageStudent)
                .collect(Collectors.toList());
    }

    public Faculty getFacultyByStudent(Long studentId) {
        return studentRepository.findById(studentId)
                .map(Student::getFaculty)
                .orElseThrow(() -> new EntityNotFoundException("Студент не найден " + studentId));
    }
}