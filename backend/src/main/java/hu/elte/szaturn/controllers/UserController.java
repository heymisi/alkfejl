/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szaturn.controllers;

import hu.elte.szaturn.entities.Course;
import hu.elte.szaturn.entities.Exam;
import hu.elte.szaturn.entities.User;
import hu.elte.szaturn.repositories.CourseRepository;
import hu.elte.szaturn.repositories.ExamRepository;
import hu.elte.szaturn.repositories.UserRepository;
import hu.elte.szaturn.security.AuthenticatedUser;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author KeresztiKrisztián
 */
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired 
    private AuthenticatedUser authenticatedUser;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ExamRepository examRepository;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        Optional<User> oUser = userRepository.findByNeptunCode(user.getNeptunCode());
        if (oUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setType(User.Role.STUDENT);
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity login() {
        return ResponseEntity.ok(authenticatedUser.getUser());
    }

    @PostMapping("/{id}/courses")
    public ResponseEntity<Course> takeCourse(@PathVariable Integer id,
                                             @RequestBody Course course) {
        System.err.println(course.getId());
        Optional<User> oStudent = userRepository.findById(id);
        Optional<Course> oCourse = courseRepository.findById(course.getId());
        if (oStudent.isPresent() && oCourse.isPresent()) {
            User user = oStudent.get();
            user.getCourses().add(oCourse.get());
            userRepository.save(user);
            return ResponseEntity.ok(oCourse.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<Iterable<Course>> getCourse(@PathVariable Integer id) {
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isPresent()) {
            return ResponseEntity.ok(oUser.get().getCourses());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/{id}/exams")
    public ResponseEntity<Exam> takeExam(@PathVariable Integer id,
                                         @RequestBody Exam exam) {
        Optional<User> oStudent = userRepository.findById(id);
        Optional<Exam> oExam = examRepository.findById(exam.getId());
        if (oStudent.isPresent() && oExam.isPresent()) {
            User user = oStudent.get();
            user.getExams().add(oExam.get());
            userRepository.save(user);
            return ResponseEntity.ok(oExam.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/exams")
    public ResponseEntity<Iterable<Exam>> getExams(@PathVariable Integer id) {
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isPresent()) {
            return ResponseEntity.ok(oUser.get().getExams());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}/courses")
    public ResponseEntity<Course> DropCourse(@PathVariable Integer id,
                                             @RequestBody Course course) {
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isPresent()) {
            Course foundCourse = oUser.get().findCourseById(course.getId());
            if (Objects.nonNull(foundCourse)) {
                User user = oUser.get();
                user.getCourses().remove(foundCourse);
                userRepository.save(user);  // have to trigger from the @JoinTable side
                return ResponseEntity.ok(foundCourse);
            }
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}/exams")
    public ResponseEntity<Exam> DropExam(@PathVariable Integer id,
                                             @RequestBody Exam exam) {
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isPresent()) {
            Exam foundExam = oUser.get().findExamById(exam.getId());
            if (Objects.nonNull(foundExam)) {
                User user = oUser.get();
                user.getExams().remove(foundExam);
                userRepository.save(user);  // have to trigger from the @JoinTable side
                return ResponseEntity.ok(foundExam);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
