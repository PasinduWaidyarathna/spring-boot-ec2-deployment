package com.example.student_managemt_system.service;

import com.example.student_managemt_system.model.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);
    Student getStudentById(String id);
    List<Student> getAllStudent();
    Student updateStudent(String id, Student student);
    void deleteStudent(String id);
    List<Student> getStudentsByYearOfEnrollment(int year);
    String getDepartmentByStudentId(String id);
    void deleteStudentsByYearOfEnrollment(int year);

}
