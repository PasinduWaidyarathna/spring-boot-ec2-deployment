package com.example.student_managemt_system.controller;

import com.example.student_managemt_system.model.Student;
import com.example.student_managemt_system.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@CrossOrigin(origins = "*")
@Tag(name="StudentController",description="This is an example description")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Create a new student
    @Operation(
            summary = "POST operation on students",
            description = "This is an example description"
    )
    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    // Get all students
    @Operation(
            summary = "GET operation on students",
            description = "This is an example description"
    )
    @GetMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudent();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // Get student by ID
    @Operation(
            summary = "GET operation on students",
            description = "This is an example description"
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    // Update student
    @Operation(
            summary = "PUT operation on students",
            description = "This is an example description"
    )
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Student> updateStudent(@PathVariable String id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        if (updatedStudent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    // Delete student by ID
    @Operation(
            summary = "DELETE operation on students",
            description = "This is an example description"
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get students by year of enrollment
    @Operation(
            summary = "GET operation on students",
            description = "This is an example description"
    )
    @GetMapping("/year/{year}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Student>> getStudentsByYear(@PathVariable int year) {
        List<Student> students = studentService.getStudentsByYearOfEnrollment(year);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // Get department by student ID
    @Operation(
            summary = "GET operation on students",
            description = "This is an example description"
    )
    @GetMapping("/department/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getDepartmentById(@PathVariable String id) {
        String department = studentService.getDepartmentByStudentId(id);
        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    // Delete students by year of enrollment
    @Operation(
            summary = "DELETE operation on students",
            description = "This is an example description"
    )
    @DeleteMapping("/year/{year}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteStudentsByYear(@PathVariable int year) {
        studentService.deleteStudentsByYearOfEnrollment(year);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
