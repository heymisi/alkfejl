package hu.elte.alkfejlbead.controllers;

import hu.elte.alkfejlbead.entities.Exam;
import hu.elte.alkfejlbead.entities.Student;
import hu.elte.alkfejlbead.entities.Teacher;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import hu.elte.alkfejlbead.repositories.ExamRepository;

@RestController
@RequestMapping("exams")
public class ExamController {

    @Autowired
    private ExamRepository examRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<Exam>> getAll() {
        return ResponseEntity.ok(examRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> get(@PathVariable Integer id) {
        Optional<Exam> exam = examRepository.findById(id);
        if (!exam.isPresent()) {
            ResponseEntity.notFound();
        }

        return ResponseEntity.ok(exam.get());
    }

    @PostMapping("")
    public ResponseEntity<Exam> post(@RequestBody Exam exam) {
        Exam newExam = examRepository.save(exam);
        return ResponseEntity.ok(newExam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Exam> exam = examRepository.findById(id);
        if (!exam.isPresent()) {
            ResponseEntity.notFound();
        }
        examRepository.delete(exam.get());
        
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> put(@PathVariable Integer id, @RequestBody Exam exam) {
        Optional<Exam> foundExam = examRepository.findById(id);
        if (!foundExam.isPresent()) {
            ResponseEntity.notFound();
        }
        
        Exam examToUpdate = foundExam.get();
        if(exam.getName() != null) {
            examToUpdate.setName(exam.getName());
        }
        if(exam.getClassRoom() != null) {
            examToUpdate.setClassRoom(exam.getClassRoom());
        }
        if(exam.getDate() != null) {
            examToUpdate.setDate(exam.getDate());
        }
        if(exam.getMaxLimit() != null) {
            examToUpdate.setMaxLimit(exam.getMaxLimit());
        }
        
        return ResponseEntity.ok(examRepository.save(examToUpdate));
    }
    
    @GetMapping("/{id}/students")
    public ResponseEntity<Iterable<Student>> students(@PathVariable Integer id) {
        Optional<Exam> oExam = examRepository.findById(id);
        if (oExam.isPresent()) {
            return ResponseEntity.ok(oExam.get().getStudents());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}/teacher")
    public ResponseEntity<Teacher> teacher(@PathVariable Integer id) {
        Optional<Exam> oExam = examRepository.findById(id);
        if (oExam.isPresent()) {
            return ResponseEntity.ok(oExam.get().getTeacher());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
