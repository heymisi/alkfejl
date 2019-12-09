package hu.elte.szaturn.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private int maxLimit;
    @Column
    @NotNull
    private boolean occupied;

    @OneToMany(mappedBy = "room",  orphanRemoval = true)
    private List<Course> courses;
    @OneToMany(mappedBy = "room",  orphanRemoval = true)
    private List<Exam> exams;
}
