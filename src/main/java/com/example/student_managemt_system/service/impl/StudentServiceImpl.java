package com.example.student_managemt_system.service.impl;

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
        Optional<Student> opt = studentRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(String id, Student updated) {
        Optional<Student> opt = studentRepository.findById(id);
        if (opt.isPresent()) {
            Student existing = opt.get();
            // update fields
            existing.setFirstName(updated.getFirstName());
            existing.setLastName(updated.getLastName());
            existing.setEmail(updated.getEmail());
            existing.setDepartment(updated.getDepartment());
            existing.setYearOfEnrollment(updated.getYearOfEnrollment());
            return studentRepository.save(existing);
        } else {
            // you might throw a custom exception here instead
            throw new RuntimeException("Student with ID " + id + " not found.");
        }
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
