package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAgeBetween(Integer minAge, Integer maxAge);

    @Query(value = "select count(*) from students s;", nativeQuery = true)
    Integer numberAllStudents();

    @Query(value = "select avg(age) from students s;", nativeQuery = true)
    Integer averageAgeStudents();

    @Query(value = "SELECT * FROM students s ORDER BY id desc limit 5;", nativeQuery = true)
    Collection<Student>  getLastFiveStudents();
}
