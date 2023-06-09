package com.crud.service;

import com.crud.model.Student;
import com.crud.model.StudentInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

        Student newStudent = createStudent(studentInfo);
        students.add(newStudent);

        return studentInfo;
    }


    public List<StudentInfo> findAll() {
        return students.stream()
                .map(s -> new StudentInfo(s.getName(), s.getEmail()))
                .toList();
    }

    public StudentInfo findById(Long id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .map(StudentService::newStudentInfo).findAny().get();
    }

    public void delete(Long id){
        Optional<Student> student = students.stream()
                .filter(s -> s.getId().equals(id))
                .findAny();

        if (student.isEmpty()) {
            return;
        }

        students.remove(student.get());
    }


    private Student createStudent(StudentInfo studentInfo) {
        return Student.builder()
                .id(this.id++)
                .name(studentInfo.getName())
                .email(studentInfo.getEmail())
                .build();
    }

    private static boolean hasStudent(StudentInfo studentInfo){
        return students.stream().anyMatch(s -> s.getName().equals(studentInfo.getName()) && s.getEmail().equals(studentInfo.getEmail()));
    }

    private static StudentInfo newStudentInfo(Student s) {
        return new StudentInfo(s.getName(), s.getEmail());
    }
}
