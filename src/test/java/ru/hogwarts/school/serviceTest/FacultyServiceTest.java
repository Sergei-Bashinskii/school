package ru.hogwarts.school.serviceTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

class FacultyServiceTest {

    private FacultyService facultyService;

    @BeforeEach
    void setUp() {
        facultyService = new FacultyService();
    }

    @Test
    void testCreateFaculty() {
        Faculty faculty = new Faculty(0, "Слизарин", "Зеленый");
        Faculty createdFaculty = facultyService.createFaculty(faculty);
        assertEquals(0, createdFaculty.getId());
        assertEquals("Слизарин", createdFaculty.getName());
        assertEquals("Зеленый", createdFaculty.getColor());
    }

    @Test
    void testReadFaculty() {
        Faculty faculty = new Faculty(0, "Гриффиндор,", "Желтый ");
        facultyService.createFaculty(faculty);
        Faculty foundFaculty = facultyService.readFaculty(0);
        assertEquals(faculty, foundFaculty);
    }

    @Test
    void testUpdateFaculty() {
        Faculty faculty = new Faculty(0, "Когтевран надо изменить", "Синий");
        facultyService.createFaculty(faculty);
        faculty.setName("Когтевран");
        Faculty updatedFaculty = facultyService.updateFaculty(faculty);
        assertEquals("Когтевран", updatedFaculty.getName());
    }

    @Test
    void testDeleteFaculty() {
        Faculty faculty = new Faculty(0, "Гриффиндор", "Желтый");
        facultyService.createFaculty(faculty);
        facultyService.deleteFaculty(0);
        assertNull(facultyService.readFaculty(0));
    }

    @Test
    void testGetFacultiesSameColor() {
        facultyService.createFaculty(new Faculty(0, "Слизарин начинающий", "Зеленый"));
        facultyService.createFaculty(new Faculty(1, "Слизарин средний", "Зеленый"));
        facultyService.createFaculty(new Faculty(2, "Слизарин конец", "Зеленый"));

        Collection<Faculty> greenFaculties = facultyService.getFacultiesSameColor("Зеленый");
        assertEquals(3, greenFaculties.size());
    }
}
