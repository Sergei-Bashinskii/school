package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.impl.FacultyService;


import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private long lastId = 0;

    public Faculty createFaculty(Faculty faculty) {
        faculties.put(lastId++, faculty);
        return faculty;
    }

    public Faculty findFaculty(Long id) {
        return faculties.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteFaculty(Long id){
        return faculties.remove(id);
    }

    public Collection<Faculty> getAllFaculties() {
        return faculties.values();
    }

    public Collection<Faculty> getByColor(String color) {
        return faculties.values().stream()
                .filter(f -> f.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
