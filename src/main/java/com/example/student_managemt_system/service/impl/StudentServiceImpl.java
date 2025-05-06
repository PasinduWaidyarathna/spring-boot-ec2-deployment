package com.example.student_managemt_system.service.impl;

import com.example.student_managemt_system.exception.ResourceNotFoundException;
import com.example.student_managemt_system.model.Student;
import com.example.student_managemt_system.repository.StudentRepository;
import com.example.student_managemt_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(String id) {

        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(String id, Student updated) {

        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot update − no student with id: " + id));

        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setEmail(updated.getEmail());
        existing.setDepartment(updated.getDepartment());
        existing.setYearOfEnrollment(updated.getYearOfEnrollment());

        return studentRepository.save(existing);

    }

    @Override
    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudentsByYearOfEnrollment(int year) {
        return studentRepository.findByYearOfEnrollment(year);
    }

    @Override
    public String getDepartmentByStudentId(String id) {
        return studentRepository.findDepartmentByStudentId(id);
    }

    @Override
    public void deleteStudentsByYearOfEnrollment(int year) {
        studentRepository.deleteByYearOfEnrollment(year);
    }
}
