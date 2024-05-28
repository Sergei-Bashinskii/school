package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.exception.EntityNotFoundException;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.service.impl.AvatarService;
import ru.hogwarts.school.service.impl.StudentService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarServiceImpl implements AvatarService {

    private final AvatarRepository avatarRepository;
    private final StudentService studentService;

    private final Logger logger = LoggerFactory.getLogger(AvatarServiceImpl.class);

    @Value("${avatars.dir.path}")
    private String avatarsDir;

    public AvatarServiceImpl(AvatarRepository avatarRepository, StudentService studentService) {
        this.avatarRepository = avatarRepository;
        this.studentService = studentService;
    }

    public Avatar findAvatar(Long studentId) {
        logger.debug("Method findAvatar was invoked.");
        return avatarRepository.findByStudentId(studentId).orElseThrow(EntityNotFoundException::new);
    }

    public Avatar findOrCreateAvatar(Long studentId) {
        logger.debug("Method findOrCreateAvatar was invoked.");
        return avatarRepository.findByStudentId(studentId).orElse(new Avatar());
    }

    public void upload(Long studentId, MultipartFile file) throws IOException {
        logger.debug("Method upload was invoked.");
        if (file != null) {
            Student student = studentService.findStudent(studentId);

            Path filePath = buildFilePath(student, file.getOriginalFilename());
            Files.createDirectories(filePath.getParent());
            Files.deleteIfExists(filePath);

            try (
                    InputStream is = file.getInputStream();
                    OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                    BufferedInputStream bis = new BufferedInputStream(is, 1024);
                    BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
            ) {
                bis.transferTo(bos);
            }

            Avatar avatar = findOrCreateAvatar(studentId);
            avatar.setStudent(student);
            avatar.setFilePath(filePath.toString());
            avatar.setFileSize(file.getSize());
            avatar.setMediaType(file.getContentType());
            avatar.setDate(file.getBytes());

            avatarRepository.save(avatar);
        } else {
            logger.warn("No file provided for upload.");
        }
    }
    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private Path buildFilePath(Student student, String fileName) {
        return Path.of(avatarsDir, student.getId() + "-" + student.getName()
                + "." + getExtensions(fileName));
    }
}
