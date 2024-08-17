package com.example.repository;

import com.example.domain.ExerciseProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseProgramRepository extends JpaRepository<ExerciseProgram , Long> {
}
