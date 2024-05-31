package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/student")
@Tag(name = "API по работе со студентами")
public class StudentController {
    private final StudentServiceImpl studentServiceImpl;

    public StudentController(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }

    @PostMapping
    @Operation(summary = "Создать студента")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student student1 = studentServiceImpl.createStudent(student);
        return ResponseEntity.ok(student);
    }

    @GetMapping("{id}")
    @Operation(summary = "Получить студента по id")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Student student = studentServiceImpl.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping(path = "/all")
    @Operation(summary = "Получить всех студентов")
    public ResponseEntity<Collection<Student>> getAllStudents() {
        Collection<Student> students = studentServiceImpl.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @PutMapping
    @Operation(summary = "Обновите информацию для студента")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student student1 = studentServiceImpl.editStudent(student);
        if (student1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student1);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удалить студента по id")
    public ResponseEntity<Student> deleteStudent(@PathVariable long id) {
        studentServiceImpl.deleteStudent(id);
        Student student = studentServiceImpl.deleteStudent(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/age-range")
    @Operation(summary = "Студенты по возрастам")
    public ResponseEntity<Collection<Student>> getStudentsByAgeRange(@RequestParam int min, @RequestParam int max) {
        Collection<Student> students = studentServiceImpl.getByAgeBetween(min, max);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}/faculty")
    @Operation(summary = "Получить факультета по студенту")
    public ResponseEntity<Faculty> getFacultyByStudent(@PathVariable Long id) {
        Faculty faculties = studentServiceImpl.getFacultyByIdStudent(id);
        return ResponseEntity.ok(faculties);
    }

    @GetMapping
    @Operation(summary = "Количество всех студентов")
    public Integer numberAllStudents() {
        return studentServiceImpl.numberAllStudents();
    }

    @GetMapping("get-avg-age")
    @Operation(summary = "Средний возраст студентов")
    public Integer averageAgeStudents() {
        return studentServiceImpl.averageAgeStudents();
    }

    @GetMapping("get-fiv-stud")
    @Operation(summary = "Пять последних студентов")
    public Collection<Student> getLastFiveStudents() {
        return studentServiceImpl.getLastFiveStudents();
    }

    @GetMapping("/Имена начинающиеся на букву А")
    public List<String> getStudentNamesStartingWithA() {
        return studentServiceImpl.getAllStudents().stream()
                .filter(student -> student.getName().toUpperCase().startsWith("А"))
                .map(student -> student.getName().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
    }

    @GetMapping("/Средний возраст")
    public Integer getAverageAge() {
        return studentServiceImpl.averageAgeStudents();
    }
}
