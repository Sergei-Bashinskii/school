package ru.hogwarts.school.service.impl;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    Student findStudent(Long id);

    Student editStudent(Student student);

    Student deleteStudent(Long id);

    Collection<Student> getAllStudents();

    Collection<Student> getByAgeBetween(Integer minAge, Integer maxAge);

    Faculty getFacultyByIdStudent(Long studentId);

    Integer numberAllStudents();

    Integer averageAgeStudents();

    Collection<Student> getLastFiveStudents();

    void printStudentNamesInParallel();

    public void printStudentNamesInSync();
}
