package com.example.student_managemt_system.service.impl;

import com.example.student_managemt_system.model.Student;
import com.example.student_managemt_system.repository.StudentRepository;
import com.example.student_managemt_system.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void testGetStudentById_found() {
        Student s = new Student();
        s.setId("1");
        s.setFirstName("John");

        when(studentRepository.findById("1")).thenReturn(Optional.of(s));

        Student result = studentService.getStudentById("1");
        assertEquals("John", result.getFirstName());
    }

    @Test
    void testGetStudentById_notFound() {
        when(studentRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> studentService.getStudentById("1"));
    }

    @Test
    void testCreateStudent() {
        Student student = new Student();
        student.setFirstName("Test");

        when(studentRepository.save(student)).thenReturn(student);

        Student created = studentService.createStudent(student);
        assertEquals("Test", created.getFirstName());
    }
}

