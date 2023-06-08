package com.crud.service;

import com.crud.model.Student;
import com.crud.model.StudentInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StudentService {
    private static final List<Student> students = new ArrayList<>();
    private Long id;

    public StudentInfo save(StudentInfo studentInfo) {
        if(hasStudent(studentInfo)){
            log.info("중복된 학생이 있습니다.");
            return null;
        }

        Student newStudent = Student.builder()
                .id(this.id++)
                .name(studentInfo.getName())
                .email(studentInfo.getEmail())
                .build();
        students.add(newStudent);

        return studentInfo;
    }

    public List<StudentInfo> readAll() {
        return students.stream()
                .map(s -> new StudentInfo(s.getName(), s.getEmail()))
                .toList();
    }

    private static boolean hasStudent(StudentInfo studentInfo){
        return students.stream().anyMatch(s -> s.getName() == studentInfo.getName() && s.getEmail() == studentInfo.getEmail());
    }
}
