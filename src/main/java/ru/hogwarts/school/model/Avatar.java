package ru.hogwarts.school.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filePath;
    private Long fileSize;
    private String mediaType;
    private byte[] date;

    @OneToOne
    private Student student;

    public Avatar() {
    }

    public Avatar(Long id, String filePath, Long fileSize, String mediaType, byte[] date, Student student) {
        this.id = id;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
        this.date = date;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getDate() {
        return date;
    }

    public void setDate(byte[] date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avatar avatar = (Avatar) o;
        return Objects.equals(id, avatar.id) && Objects.equals(filePath, avatar.filePath) && Objects.equals(fileSize, avatar.fileSize) && Objects.equals(mediaType, avatar.mediaType) && Arrays.equals(date, avatar.date) && Objects.equals(student, avatar.student);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, filePath, fileSize, mediaType, student);
        result = 31 * result + Arrays.hashCode(date);
        return result;
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", date=" + Arrays.toString(date) +
                ", student=" + student +
                '}';
    }
}
