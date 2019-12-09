/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private Integer maxLimit;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
   // @JoinColumn
    private Room room;

    private Integer roomNumber;

    private String date;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> students;

}
