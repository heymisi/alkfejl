package hu.elte.szaturn.entities;


import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author KeresztiKrisztián
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private Integer maxLimit;

    @Column
    private Integer roomNumber;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Room room;

    private String date;

    @ManyToMany(mappedBy = "exams", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> students;

}
