package hu.elte.szaturn.controllers;

import hu.elte.szaturn.entities.Exam;
import hu.elte.szaturn.entities.Room;
import hu.elte.szaturn.entities.User;
import hu.elte.szaturn.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("/exams")
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
        if(exam.getRoom() != null) {
            examToUpdate.setRoom(exam.getRoom());
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
    public ResponseEntity<Iterable<User>> students(@PathVariable Integer id) {
        Optional<Exam> oExam = examRepository.findById(id);
        if (oExam.isPresent()) {
            return ResponseEntity.ok(oExam.get().getStudents());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}/room")
    public ResponseEntity<Room> room(@PathVariable Integer id) {
        Optional<Exam> oExam = examRepository.findById(id);
        if (oExam.isPresent()) {
            return ResponseEntity.ok(oExam.get().getRoom());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}