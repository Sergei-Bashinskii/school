package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.EntityNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.impl.StudentService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final HashMap<Long, Student> students = new HashMap<>();
    private long lastId = 0;

    public Student createStudent(Student student) {
        students.put(lastId++, student);
        return student;
    }

    public Student findStudent(Long id) {
        if (students.containsKey(id)) {
            return students.get(id);
        }
        throw new EntityNotFoundException();
    }

    public Student editStudent(Student student) {
        if (students.containsKey(student.getId())) {
            return students.put(student.getId(), student);
        }
        throw new EntityNotFoundException();
    }

    public Student deleteStudent(Long id){
        if (students.containsKey(id)) {
            return students.remove(id);
        }
        throw new EntityNotFoundException();
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }

    public Collection<Student> getByAge(Integer age) {
        return students.values().stream()
                .filter(s -> s.getAge().equals(age))
                .collect(Collectors.toList());
    }
}