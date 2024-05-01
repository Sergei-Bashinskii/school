package ru.hogwarts.school.service.impl;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import java.io.IOException;

public interface AvatarService {

    Avatar findAvatar(Long studentId);

    Avatar findOrCreateAvatar(Long studentId);

    void upload(Long studentId, MultipartFile file) throws IOException;
}
