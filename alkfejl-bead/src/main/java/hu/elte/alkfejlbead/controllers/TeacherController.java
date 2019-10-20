package hu.elte.alkfejlbead.controllers;

import hu.elte.alkfejlbead.entities.Course;
import hu.elte.alkfejlbead.entities.Exam;
import hu.elte.alkfejlbead.entities.Teacher;
import hu.elte.alkfejlbead.repositories.CourseRepository;
import hu.elte.alkfejlbead.repositories.ExamRepository;
import hu.elte.alkfejlbead.repositories.TeacherRepository;
import java.util.Objects;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ExamRepository examRepository;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("")
    public ResponseEntity<Iterable<Teacher>> getAll() {
        return ResponseEntity.ok(teacherRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> get(@PathVariable Integer id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (!teacher.isPresent()) {
            ResponseEntity.notFound();
        }

        return ResponseEntity.ok(teacher.get());
    }

    @PostMapping("")
    public ResponseEntity<Teacher> post(@RequestBody Teacher teacher) {
        Teacher newTeacher = teacherRepository.save(teacher);
        return ResponseEntity.ok(newTeacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (!teacher.isPresent()) {
            ResponseEntity.notFound();
        }
        teacherRepository.delete(teacher.get());

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> put(@PathVariable Integer id, @RequestBody Teacher teacher) {
        Optional<Teacher> foundTeacher = teacherRepository.findById(id);
        if (!foundTeacher.isPresent()) {
            ResponseEntity.notFound();
        }
        
        Teacher teacherToUpdate = foundTeacher.get();
        if(teacher.getNeptunCode() != null) {
            teacherToUpdate.setNeptunCode(teacher.getNeptunCode());
        }
        if(teacher.getPassword() != null) {
            teacherToUpdate.setPassword(teacher.getPassword());
        }
        if(teacher.getName() != null) {
            teacherToUpdate.setName(teacher.getName());
        }
        
        return ResponseEntity.ok(teacherRepository.save(teacherToUpdate));
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<Iterable<Course>> courses(@PathVariable Integer id) {
        Optional<Teacher> oTeacher = teacherRepository.findById(id);
        if (oTeacher.isPresent()) {
            return ResponseEntity.ok(oTeacher.get().getCourses());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/courses")
    public ResponseEntity<Teacher> takeCourse(@PathVariable Integer id,
            @RequestParam(value = "courseId") Integer courseId) {
        Optional<Teacher> oTeacher = teacherRepository.findById(id);
        Optional<Course> oCourse = courseRepository.findById(courseId);
        if (oTeacher.isPresent() && oCourse.isPresent()) {
            Teacher teacher = oTeacher.get();
            teacher.getCourses().add(oCourse.get());
            teacherRepository.save(teacher);  // have to trigger from the @JoinTable side
            return ResponseEntity.ok(teacher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/courses")
    public ResponseEntity<Course> DropCourse(@PathVariable Integer id,
            @RequestParam(value = "courseId") Integer courseId) {
        Optional<Teacher> oTeacher = teacherRepository.findById(id);
        if (oTeacher.isPresent()) {
            Course foundCourse = oTeacher.get().findCourseById(courseId);
            if (Objects.nonNull(foundCourse)) {
                Teacher teacher = oTeacher.get();
                teacher.getCourses().add(foundCourse);
                teacherRepository.save(teacher);  // have to trigger from the @JoinTable side
                return ResponseEntity.ok(foundCourse);
            }
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/exams")
    public ResponseEntity<Iterable<Exam>> exams(@PathVariable Integer id) {
        Optional<Teacher> oTeacher = teacherRepository.findById(id);
        if (oTeacher.isPresent()) {
            return ResponseEntity.ok(oTeacher.get().getExams());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/exams")
    public ResponseEntity<Exam> takeExam(@PathVariable Integer id,
            @RequestParam(value = "examId") Integer examId) {
        Optional<Teacher> oTeacher = teacherRepository.findById(id);
        Optional<Exam> oExam = examRepository.findById(examId);
        if (oTeacher.isPresent() && oExam.isPresent()) {
            Teacher teacher = oTeacher.get();
            teacher.getExams().add(oExam.get());
            teacherRepository.save(teacher);  // have to trigger from the @JoinTable side
            return ResponseEntity.ok(oExam.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}/exams")
    public ResponseEntity<Exam> DropExam(@PathVariable Integer id,
            @RequestParam(value = "examId") Integer examId) {
        Optional<Teacher> oTeacher = teacherRepository.findById(id);
        if (oTeacher.isPresent()) {
            Exam foundExam = oTeacher.get().findExamById(examId);
            if (Objects.nonNull(foundExam)) {
                Teacher teacher = oTeacher.get();
                teacher.getExams().add(foundExam);
                teacherRepository.save(teacher);  // have to trigger from the @JoinTable side
                return ResponseEntity.ok(foundExam);
            }
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("register")
    public ResponseEntity<Teacher> register(@RequestBody Teacher teacher) {
        Optional<Teacher> oTeacher = teacherRepository.findByNeptunCode(teacher.getNeptunCode());
        if (oTeacher.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
//        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        return ResponseEntity.ok(teacherRepository.save(teacher));
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody Teacher teacher) {
        return ResponseEntity.ok().build();
    }
}
