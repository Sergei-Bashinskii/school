package ru.hogwarts.school.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty readFaculty(long id) {
        return facultyRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty deleteFaculty(long id) {
        Faculty faculty = readFaculty(id);
        facultyRepository.deleteById(id);
        return faculty;
    }

    public List<Faculty> findFacultiesByNameOrColor(String search) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(search, search);
    }


    public Collection<Faculty> getFacultiesSameColor(String color) {
        return facultyRepository.findAll().stream()
                .filter(f -> f.getColor().equals(color))
                .collect(Collectors.toList());
    }

    public Set<Student> getStudentsByFaculty(Long facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new EntityNotFoundException("Факультет не найден " + facultyId));
        return faculty.getStudents();
    }
}
