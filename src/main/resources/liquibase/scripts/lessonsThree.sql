-- liquibase formatted sql

--changeset user1:1

CREATE INDEX student_name_index ON students (name);

CREATE INDEX faculty_name_color_index ON faculties (name, color);