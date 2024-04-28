package ru.hogwarts.school.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyServiceImpl;

import java.util.Collection;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceImplTest {

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private FacultyServiceImpl facultyServiceImpl;

    private Faculty faculty;

    @BeforeEach
    public void setUp() {
        faculty = new Faculty();
        faculty.setId(1L);
        faculty.setColor("Red");
        faculty.setName("Gryffindor");
    }

    @Test
    public void testCreateFaculty() {
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        Faculty created = facultyServiceImpl.createFaculty(faculty);
        assertNotNull(created);
        verify(facultyRepository).save(faculty);
    }

    @Test
    public void testReadFaculty() {
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));

        Faculty found = facultyServiceImpl.getFaculty(1L);
        assertNotNull(found);
        assertEquals("Red", found.getColor());
    }

    @Test
    public void testUpdateFaculty() {
        when(facultyRepository.save(faculty)).thenReturn(faculty);

        Faculty updated = facultyServiceImpl.updateFaculty(faculty);
        assertNotNull(updated);
        verify(facultyRepository).save(faculty);
    }

    @Test
    public void testDeleteFaculty() {
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));
        doNothing().when(facultyRepository).deleteById(1L);

        Faculty deleted = facultyServiceImpl.deleteFaculty(1L);
        assertNotNull(deleted);
        verify(facultyRepository).deleteById(1L);
    }

    @Test
    public void testGetFacultiesSameColor() {
        List<Faculty> faculties = new ArrayList<>();
        faculties.add(faculty);
        when(facultyRepository.findAll()).thenReturn(faculties);

        Collection<Faculty> result = facultyServiceImpl.getFacultiesSameColor("Red");
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}