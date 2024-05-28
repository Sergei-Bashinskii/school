package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.EntityNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.impl.StudentService;

import java.util.Collection;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("A method was called to create a student");
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        logger.info("A method was called to search for a student");
        return studentRepository.findById(id).orElseThrow(
                () -> {logger.error("There are no students with id =" + id);
                return new EntityNotFoundException();
                });
    }

    public Student editStudent(Student student) {
        logger.info("A method was called to edit the student");
        return studentRepository.save(student);
    }

    public Student deleteStudent(Long id){
        logger.info("A method was called to delete the student");
        Student student = findStudent(id);
        studentRepository.deleteById(id);
        return student;
    }

    public Collection<Student> getAllStudents() {
        logger.info("A method was called to get all the students");
        return studentRepository.findAll();
    }
    
    public Collection<Student> getByAgeBetween(Integer minAge, Integer maxAge) {
        logger.info("A method was called to get students by age");
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public Faculty getFacultyByIdStudent(Long studentId) {
        logger.info("The method of obtaining faculty by student id was called");
        return findStudent(studentId).getFaculty();
    }

    public Integer numberAllStudents(){
        logger.info("A method was called to get the number of all students");
        return studentRepository.numberAllStudents();
    }

    public Integer averageAgeStudents() {
        logger.info("A method was called to obtain the average age of students");
        return studentRepository.averageAgeStudents();
    }

    public Collection<Student> getLastFiveStudents() {
        logger.info("A method was called to get the last five students of the students");
        return studentRepository.getLastFiveStudents();
    }
}