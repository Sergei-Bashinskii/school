package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.EntityNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.impl.FacultyService;


import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    private static final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);


    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long id) {
        logger.info("Was invoked method for find faculty");
        return facultyRepository.findById(id).orElseThrow(() -> {
            logger.error("There is not faculty with id = " + id);
            return new EntityNotFoundException();
        });
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Was invoked method for edit faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty deleteFaculty(Long id){
        logger.info("Was invoked method for delete faculty");
        Faculty faculty = findFaculty(id);
        facultyRepository.deleteById(id);
        return faculty;
    }

    public Collection<Faculty> getAllFaculties() {
        logger.info("Was invoked method for get all faculties");
        return facultyRepository.findAll();
    }

    public Collection<Faculty> getByColorOrName(String color, String name) {
        logger.info("Was invoked method for get faculties by color or name");
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

    public Collection<Student> getStudentFaculty(Long facultyId) {
        logger.info("Was invoked method for get students of faculty");
        return findFaculty(facultyId).getStudent();
    }
}
