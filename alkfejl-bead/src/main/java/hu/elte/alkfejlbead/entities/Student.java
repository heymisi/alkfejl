
package hu.elte.alkfejlbead.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(nullable = false)
    private String neptunCode;
    
    @Column(nullable = false)
    private String password;
    
    private String name;
    
    @JsonInclude(Include.NON_NULL)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<Course> courses;
    
    @JsonInclude(Include.NON_NULL)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<Exam> exams;
    
    public Course findCourseById(Integer courseId) {
        for(Course course : courses) {
            if(course.getId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    public Exam findExamById(Integer examId) {
        for(Exam exam : exams) {
            if(exam.getId().equals(examId)) {
                return exam;
            }
        }
        return null;
    }
}
