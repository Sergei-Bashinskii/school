package ru.hogwarts.school.serviceTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyServiceImpl;

import java.util.Collection;

class FacultyServiceImplTest {

    private FacultyServiceImpl facultyServiceImpl;

    @BeforeEach
    void setUp() {
        facultyServiceImpl = new FacultyServiceImpl();
    }

    @Test
    void testCreateFaculty() {
        Faculty faculty = new Faculty(0, "Слизарин", "Зеленый");
        Faculty createdFaculty = facultyServiceImpl.createFaculty(faculty);
        assertEquals(0, createdFaculty.getId());
        assertEquals("Слизарин", createdFaculty.getName());
        assertEquals("Зеленый", createdFaculty.getColor());
    }

    @Test
    void testReadFaculty() {
        Faculty faculty = new Faculty(0, "Гриффиндор,", "Желтый ");
        facultyServiceImpl.createFaculty(faculty);
        Faculty foundFaculty = facultyServiceImpl.findFaculty(0L);
        assertEquals(faculty, foundFaculty);
    }

    @Test
    void testUpdateFaculty() {
        Faculty faculty = new Faculty(0, "Когтевран надо изменить", "Синий");
        facultyServiceImpl.createFaculty(faculty);
        faculty.setName("Когтевран");
        Faculty updatedFaculty = facultyServiceImpl.editFaculty(faculty);
        assertEquals("Когтевран", updatedFaculty.getName());
    }

    @Test
    void testDeleteFaculty() {
        Faculty faculty = new Faculty(0, "Гриффиндор", "Желтый");
        facultyServiceImpl.createFaculty(faculty);
        facultyServiceImpl.deleteFaculty(0L);
        assertNull(facultyServiceImpl.findFaculty(0L));
    }

    @Test
    void testGetFacultiesSameColor() {
        facultyServiceImpl.createFaculty(new Faculty(0, "Слизарин начинающий", "Зеленый"));
        facultyServiceImpl.createFaculty(new Faculty(1, "Слизарин средний", "Зеленый"));
        facultyServiceImpl.createFaculty(new Faculty(2, "Слизарин конец", "Зеленый"));

        Collection<Faculty> greenFaculties = facultyServiceImpl.getAllFaculties();
        assertEquals(3, greenFaculties.size());
    }
}
