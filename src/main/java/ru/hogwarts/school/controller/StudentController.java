package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        Student addedStudent = studentService.addStudent(student);
        return ResponseEntity.ok(addedStudent);
    }
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        Student student = studentService.getStudentById(id);
        if (student == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }
    @GetMapping("/all")
    public ResponseEntity<Collection<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student){
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudentById(@PathVariable Long id){
        Student deletedStudent = studentService.deleteStudentById(id);
        return ResponseEntity.ok(deletedStudent);
    }
    @GetMapping("/filter")
    public ResponseEntity<List<Student>> getStudentsByAgeBetween(@RequestParam int ageFrom, @RequestParam int ageTo){
        return ResponseEntity.ok(studentService.getStudentsByAgeBetween(ageFrom,ageTo));
    }

    @GetMapping("{id}/faculty")
    public ResponseEntity<Faculty> getFacultyByStudentId(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getFacultyByStudentId(id));
    }
}
