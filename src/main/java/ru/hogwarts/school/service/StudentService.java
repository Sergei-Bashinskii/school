package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class StudentService {

    private final HashMap<Long, Student> students = new HashMap<>();
    private long id = 0;

    public Student createStudent(Student student) {
        student.setId(id++);
        students.put(student.getId(), student);
        return student;
    }

    public Student readStudent(long id) {
        return students.get(id);
    }

    public Student updateStudent(Student student) {
        if (!students.containsKey(student.getId())) {
            return null;
        }
        students.put(student.getId(), student);
        return student;
    }

    public Student deleteStudent(long id) {
        return students.remove(id);
    }

    public Collection<Student> getStudentsSameAge(int ageStudent) {
        ArrayList<Student> studentsSameAge = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getAge() == ageStudent) {
                studentsSameAge.add(student);
            }
        }
        return studentsSameAge;
    }
}