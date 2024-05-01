package ru.hogwarts.school.service.impl;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;


import java.util.Collection;

public interface FacultyService {

    Faculty createFaculty(Faculty faculty);

    Faculty findFaculty(Long id);

    Faculty editFaculty(Faculty faculty);

    Faculty deleteFaculty(Long id);

    Collection<Faculty> getAllFaculties();

    Collection<Faculty> getByColorOrName(String color, String name);

    Collection<Student> getStudentFaculty(Long facultyId);
}
