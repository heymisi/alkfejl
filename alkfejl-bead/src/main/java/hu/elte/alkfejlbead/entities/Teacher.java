
package hu.elte.alkfejlbead.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Teacher implements Serializable  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(nullable = false)
    private String neptunCode;
    
    @Column(nullable = false)
    private String password;
    
    private String name;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses;

    @JsonManagedReference
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
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
