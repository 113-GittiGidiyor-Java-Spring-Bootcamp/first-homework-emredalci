package dev.patika.controller;

import dev.patika.models.Course;
import dev.patika.models.Student;
import dev.patika.service.StudentService;

import java.util.List;

public class StudentController {

    private StudentService studentService = new StudentService();

    public List<Student> findAll() {
        return studentService.findAll();
    }

    public Student findById(int studentId) {
        return studentService.findById(studentId);
    }

    public void saveToDatabase(Student student) {
        studentService.saveToDatabase(student);
    }

    public void deleteFromDatabase(Student student) {
        studentService.deleteFromDatabase(student);
    }

    public void updateOnDatabase(Student student, int studentId) {
        studentService.updateOnDatabase(student,studentId);
    }

    public void deleteStudentFromDatabase(int id) {
        studentService.deleteStudentFromDatabase(id);
    }



}
