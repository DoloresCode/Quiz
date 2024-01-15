package com.challenge.demo.repositories;

import com.challenge.demo.entities.MatrixQuestionRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatrixQuestionRowRepository extends JpaRepository<MatrixQuestionRow, Long> {
}
