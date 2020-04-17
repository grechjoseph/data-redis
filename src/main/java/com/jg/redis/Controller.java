package com.jg.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class Controller {

    private final StudentRepository studentRepository;

    @PostMapping
    public Student addStudent(@RequestBody final Student student) {
        return studentRepository.save(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    @GetMapping("/{studentId}")
    public Student findStudentById(@PathVariable final UUID studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student with ID " +  studentId + " could not be found."));
    }

    @PutMapping("/{studentId}")
    public Student updateStudent(@RequestBody final Student student, @PathVariable final UUID studentId) {
        student.setId(studentId);
        return studentRepository.save(student);
    }

    @DeleteMapping("/{studentId}")
    public List<Student> deleteStudentById(@PathVariable final UUID studentId) {
        studentRepository.delete(findStudentById(studentId));
        return getAllStudents();
    }

}
