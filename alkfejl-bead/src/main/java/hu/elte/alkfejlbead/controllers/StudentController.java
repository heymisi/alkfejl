package hu.elte.alkfejlbead.controllers;


import hu.elte.alkfejlbead.entities.Course;
import hu.elte.alkfejlbead.entities.Exam;
import hu.elte.alkfejlbead.entities.Student;
import hu.elte.alkfejlbead.repositories.CourseRepository;
import hu.elte.alkfejlbead.repositories.ExamRepository;
import hu.elte.alkfejlbead.repositories.StudentRepository;
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
@RequestMapping("students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ExamRepository examRepository;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("")
    public ResponseEntity<Iterable<Student>> getAll() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> get(@PathVariable Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            ResponseEntity.notFound();
        }

        return ResponseEntity.ok(student.get());
    }

    @PostMapping("")
    public ResponseEntity<Student> post(@RequestBody Student student) {
        Student newStudent = studentRepository.save(student);
        return ResponseEntity.ok(newStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            ResponseEntity.notFound();
        }
        studentRepository.delete(student.get());

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> put(@PathVariable Integer id, @RequestBody Student student) {
        Optional<Student> foundStudent = studentRepository.findById(id);
        if (!foundStudent.isPresent()) {
            ResponseEntity.notFound();
        }
        
        Student studentToUpdate = foundStudent.get();
        if(student.getNeptunCode() != null) {
            studentToUpdate.setNeptunCode(student.getNeptunCode());
        }
        if(student.getPassword() != null) {
            studentToUpdate.setPassword(student.getPassword());
        }
        if(student.getName() != null) {
            studentToUpdate.setName(student.getName());
        }
        
        return ResponseEntity.ok(studentRepository.save(studentToUpdate));
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<Iterable<Course>> courses(@PathVariable Integer id) {
        Optional<Student> oStudent = studentRepository.findById(id);
        if (oStudent.isPresent()) {
            return ResponseEntity.ok(oStudent.get().getCourses());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/courses")
    public ResponseEntity<Course> takeCourse(@PathVariable Integer id,
            @RequestParam(value = "courseId") Integer courseId) {
        Optional<Student> oStudent = studentRepository.findById(id);
        Optional<Course> oCourse = courseRepository.findById(courseId);
        if (oStudent.isPresent() && oCourse.isPresent()) {
            Student student = oStudent.get();
            student.getCourses().add(oCourse.get());
            studentRepository.save(student);  // have to trigger from the @JoinTable side
            return ResponseEntity.ok(oCourse.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/courses")
    public ResponseEntity<Course> DropCourse(@PathVariable Integer id,
            @RequestParam(value = "courseId") Integer courseId) {
        Optional<Student> oStudent = studentRepository.findById(id);
        if (oStudent.isPresent()) {
            Course foundCourse = oStudent.get().findCourseById(courseId);
            if (Objects.nonNull(foundCourse)) {
                Student student = oStudent.get();
                student.getCourses().add(foundCourse);
                studentRepository.save(student);  // have to trigger from the @JoinTable side
                return ResponseEntity.ok(foundCourse);
            }
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/exams")
    public ResponseEntity<Iterable<Exam>> exams(@PathVariable Integer id) {
        Optional<Student> oStudent = studentRepository.findById(id);
        if (oStudent.isPresent()) {
            return ResponseEntity.ok(oStudent.get().getExams());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/exams")
    public ResponseEntity<Exam> takeExam(@PathVariable Integer id,
            @RequestParam(value = "examId") Integer examId) {
        Optional<Student> oStudent = studentRepository.findById(id);
        Optional<Exam> oExam = examRepository.findById(examId);
        if (oStudent.isPresent() && oExam.isPresent()) {
            Student student = oStudent.get();
            student.getExams().add(oExam.get());
            studentRepository.save(student);  // have to trigger from the @JoinTable side
            return ResponseEntity.ok(oExam.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}/exams")
    public ResponseEntity<Exam> DropExam(@PathVariable Integer id,
            @RequestParam(value = "examId") Integer examId) {
        Optional<Student> oStudent = studentRepository.findById(id);
        if (oStudent.isPresent()) {
            Exam foundExam = oStudent.get().findExamById(examId);
            if (Objects.nonNull(foundExam)) {
                Student student = oStudent.get();
                student.getExams().add(foundExam);
                studentRepository.save(student);  // have to trigger from the @JoinTable side
                return ResponseEntity.ok(foundExam);
            }
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("register")
    public ResponseEntity<Student> register(@RequestBody Student student) {
        Optional<Student> oStudent = studentRepository.findByNeptunCode(student.getNeptunCode());
        if (oStudent.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
//        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody Student student) {
        return ResponseEntity.ok().build();
    }
}
