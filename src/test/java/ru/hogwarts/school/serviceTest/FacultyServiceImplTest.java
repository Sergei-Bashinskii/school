package ru.hogwarts.school.serviceTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.exception.EntityNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;

class facultyRepositoryTest {

    private FacultyRepository facultyRepository;

    public facultyRepositoryTest(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @BeforeEach
    

    @Test
    void testsave() {
        Faculty faculty = new Faculty(0, "Слизарин", "Зеленый");
        Faculty createdFaculty = facultyRepository.save(faculty);
        assertEquals(0, createdFaculty.getId());
        assertEquals("Слизарин", createdFaculty.getName());
        assertEquals("Зеленый", createdFaculty.getColor());
    }

    @Test
    void testReadFaculty() {
        Faculty faculty = new Faculty(0, "Гриффиндор,", "Желтый ");
        facultyRepository.save(faculty);
        Faculty foundFaculty = facultyRepository.findById(faculty.getId()).orElseThrow(EntityNotFoundException::new);
        assertEquals(faculty, foundFaculty);
    }

    @Test
    void testUpdateFaculty() {
        Faculty faculty = new Faculty(0, "Когтевран надо изменить", "Синий");
        facultyRepository.save(faculty);
        faculty.setName("Когтевран");
        Faculty updatedFaculty = facultyRepository.save(faculty);
        assertEquals("Когтевран", updatedFaculty.getName());
    }

    @Test
    void testDeleteFaculty() {
        Faculty faculty = new Faculty(0, "Гриффиндор", "Желтый");
        facultyRepository.save(faculty);
        facultyRepository.deleteById(0L);
        assertNull(facultyRepository.findById(0L));
    }

    @Test
    void testGetFacultiesSameColor() {
        facultyRepository.save(new Faculty(0, "Слизарин начинающий", "Зеленый"));
        facultyRepository.save(new Faculty(1, "Слизарин средний", "Зеленый"));
        facultyRepository.save(new Faculty(2, "Слизарин конец", "Зеленый"));

        Collection<Faculty> greenFaculties = facultyRepository.findAll();
        assertEquals(3, greenFaculties.size());
    }
}
