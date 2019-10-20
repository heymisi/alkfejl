package hu.elte.alkfejlbead.controllers;

import hu.elte.alkfejlbead.entities.Course;
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
import hu.elte.alkfejlbead.repositories.CourseRepository;

@RestController
@RequestMapping("courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<Course>> getAll() {
        return ResponseEntity.ok(courseRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> get(@PathVariable Integer id) {
        Optional<Course> course = courseRepository.findById(id);
        if (!course.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(course.get());
    }

    @PostMapping("")
    public ResponseEntity<Course> post(@RequestBody Course course) {
        Course newCourse = courseRepository.save(course);
        return ResponseEntity.ok(newCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Course> course = courseRepository.findById(id);
        if (!course.isPresent()) {
            ResponseEntity.notFound();
        }
        courseRepository.delete(course.get());
        
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> put(@PathVariable Integer id, @RequestBody Course course) {
        Optional<Course> foundCourse = courseRepository.findById(id);
        if (!foundCourse.isPresent()) {
            ResponseEntity.notFound();
        }
        
        Course courseToUpdate = foundCourse.get();
        if(course.getName() != null) {
            courseToUpdate.setName(course.getName());
        }
        if(course.getClassRoom() != null) {
            courseToUpdate.setClassRoom(course.getClassRoom());
        }
        if(course.getDate() != null) {
            courseToUpdate.setDate(course.getDate());
        }
        if(course.getMaxLimit() != null) {
            courseToUpdate.setMaxLimit(course.getMaxLimit());
        }
        
        return ResponseEntity.ok(courseRepository.save(courseToUpdate));
    }
    
    @GetMapping("/{id}/students")
    public ResponseEntity<Iterable<Student>> students(@PathVariable Integer id) {
        Optional<Course> oCurse = courseRepository.findById(id);
        if (oCurse.isPresent()) {
            return ResponseEntity.ok(oCurse.get().getStudents());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}/teacher")
    public ResponseEntity<Teacher> teacher(@PathVariable Integer id) {
        Optional<Course> oCurse = courseRepository.findById(id);
        if (oCurse.isPresent()) {
            return ResponseEntity.ok(oCurse.get().getTeacher());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
