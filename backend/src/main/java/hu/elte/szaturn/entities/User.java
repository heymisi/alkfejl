/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szaturn.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 *
 * @author KeresztiKrisztián
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String neptunCode;

    private String name;
    
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Course> courses;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Exam> exams;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role type;
    
    public enum Role {
        STUDENT, TEACHER
    }
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
