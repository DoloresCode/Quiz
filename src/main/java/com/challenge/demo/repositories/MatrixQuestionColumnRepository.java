package com.challenge.demo.repositories;

import com.challenge.demo.entities.MatrixQuestionColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatrixQuestionColumnRepository extends JpaRepository<MatrixQuestionColumn, Long> {
}
