package com.example.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ExerciseProgram {

    @Id @GeneratedValue
    @Column(name = "exercise_program_id")
    private Long id;


    @Column(name = "exercise_program_name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String description;
}
