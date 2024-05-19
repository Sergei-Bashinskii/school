package ru.hogwarts.school.repository;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Avatar;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Optional<Avatar> findByStudentId(Long studentId);

    List<Avatar> getAvatarsByPage(int pageNumber, int pageSize);

    Page findAll(Pageable pageable);
}
