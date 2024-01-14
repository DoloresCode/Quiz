package com.challenge.demo.controllers;


import com.challenge.demo.entities.MatrixQuestion;
import com.challenge.demo.entities.MatrixQuestionColumn;
import com.challenge.demo.entities.MatrixQuestionRow;
import com.challenge.demo.entities.dtos.MatrixQuestionDTO;
import com.challenge.demo.repositories.SiteRepository;
import com.challenge.demo.services.MatrixQuestionService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/matrix-questions")
public class MatrixQuestionController {

    @Autowired
    private MatrixQuestionService matrixQuestionService;


    @Autowired
    private SiteRepository siteRepository;

    /**
     * Creates or updates a MatrixQuestion. If the associated site is found, the incoming DTO is converted to an entity and saved or updated.
     * If the site is not found, a 404 status is returned.
     * @param incomingQuestion The incoming MatrixQuestionDTO.
     * @return ResponseEntity with the MatrixQuestionDTO and HTTP status.
     */
    @PostMapping
    public ResponseEntity<MatrixQuestionDTO> createOrUpdateMatrixQuestion(@RequestBody MatrixQuestionDTO incomingQuestion) {
        return siteRepository
                .findById(incomingQuestion.getSiteId())
                .map(site -> {
                    final MatrixQuestion newQ = MatrixQuestionDTO.toEntity(incomingQuestion, site);
                    return new ResponseEntity<>(MatrixQuestionDTO.fromEntity(matrixQuestionService.saveOrUpdateMatrixQuestion(newQ)), HttpStatus.CREATED);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new row for MatrixQuestion with a POST mapping method to /matrix-questions
    // Use URL like /matrix-questions/{matrixQuestionID}/rows - "http://localhost:8080/matrix-questions/1"
    @PostMapping("/{matrixQuestionID}/rows")
    public ResponseEntity<MatrixQuestionRow> createRow(@PathVariable Long matrixQuestionID, @RequestBody MatrixQuestionRow row) {
        MatrixQuestion matrixQuestion = matrixQuestionService.getMatrixQuestionById(matrixQuestionID);
        row.setMatrixQuestion(matrixQuestion);
        MatrixQuestionRow matrixQuestionRow = matrixQuestionService.saveOrUpdateMatrixQuestionRow(row);
        return new ResponseEntity<>(matrixQuestionRow, HttpStatus.CREATED);
    }

    // Create a new column for MatrixQuestion with a POST mapping method to /matrix-questions
    // Use URL like /matrix-questions/{matrixQuestionID}/columns - "http://localhost:8080/matrix-questions/1"
    @PostMapping("/{matrixQuestionID}/columns")
    public ResponseEntity<MatrixQuestionColumn> createColumn(@PathVariable Long matrixQuestionID, @RequestBody MatrixQuestionColumn column) {
        MatrixQuestion matrixQuestion = matrixQuestionService.getMatrixQuestionById(matrixQuestionID);
        column.setMatrixQuestion(matrixQuestion);
        MatrixQuestionColumn matrixQuestionColumn = matrixQuestionService.saveOrUpdateMatrixQuestionColumn(column);
        return new ResponseEntity<>(matrixQuestionColumn, HttpStatus.CREATED);
    }

    // Use URL like /matrix_questions/{id}/rows - "http://localhost:8080/matrix-questions/1"
    @GetMapping("/{id}/rows")
    public ResponseEntity<List<MatrixQuestionRow>> getMatrixQuestionRows(@PathVariable(value = "id") Long matrixQuestionId) {
        MatrixQuestion matrixQuestion = matrixQuestionService.getMatrixQuestionById(matrixQuestionId);
        Hibernate.initialize(matrixQuestion.getRows());
        return new ResponseEntity<>(matrixQuestion.getRows(), HttpStatus.OK);
    }

    // Use URL like /matrix_questions/{id}/columns - "http://localhost:8080/matrix-questions/1"
    @GetMapping("/{id}/columns")
    public ResponseEntity<Set<MatrixQuestionColumn>> getMatrixQuestionColumns(@PathVariable(value = "id") Long matrixQuestionId) {
        MatrixQuestion matrixQuestion = matrixQuestionService.getMatrixQuestionById(matrixQuestionId);
        Hibernate.initialize(matrixQuestion.getColumns());
        return new ResponseEntity<>(matrixQuestion.getColumns(), HttpStatus.OK);
    }

    // update the specified row for the specified matrix question
    // Use URL like /matrix-questions/{matrixQuestionID}/rows/{rowIndex} - "http://localhost:8080/matrix-questions/1/rows/0"
    @PutMapping("/{matrixQuestionID}/rows/{rowIndex}")
    public ResponseEntity<MatrixQuestion> updateRow(@PathVariable Long matrixQuestionID, @PathVariable int rowIndex, @RequestBody String updatedRow) {
        MatrixQuestion matrixQuestion = matrixQuestionService.getMatrixQuestionById(matrixQuestionID);
        //matrixQuestion.getRows().set(rowIndex, updatedRow);
        MatrixQuestion updatedMatrixQuestion = matrixQuestionService.saveOrUpdateMatrixQuestion(matrixQuestion);
        return new ResponseEntity<>(updatedMatrixQuestion, HttpStatus.OK);
    }

    // update the specified column for the specified matrix question
    // Use URL like /matrix-questions/{matrixQuestionID}/columns/{columnIndex} -  "http://localhost:8080/matrix-questions/1/columns/0"
    @PutMapping("/{matrixQuestionID}/columns/{columnIndex}")
    public ResponseEntity<MatrixQuestion> updateColumn(@PathVariable Long matrixQuestionID, @PathVariable int columnIndex, @RequestBody String updatedColumn) {
        MatrixQuestion matrixQuestion = matrixQuestionService.getMatrixQuestionById(matrixQuestionID);
        //matrixQuestion.getColumns().set(columnIndex, updatedColumn);
        MatrixQuestion updatedMatrixQuestion = matrixQuestionService.saveOrUpdateMatrixQuestion(matrixQuestion);
        return new ResponseEntity<>(updatedMatrixQuestion, HttpStatus.OK);
    }

    // delete the specified row for the specified matrix question
    // Use URL like /matrix-questions/{matrixQuestionID}/rows/{rowIndex}
    @DeleteMapping("/{matrixQuestionID}/rows/{rowIndex}")
    public ResponseEntity<Void> deleteRow(@PathVariable Long matrixQuestionID, @PathVariable int rowIndex) {
        MatrixQuestion matrixQuestion = matrixQuestionService.getMatrixQuestionById(matrixQuestionID);
        matrixQuestion.getRows().remove(rowIndex);
        matrixQuestionService.saveOrUpdateMatrixQuestion(matrixQuestion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // delete the specified column for the specified matrix question
    // Use URL like /matrix-questions/{matrixQuestionID}/columns/{columnIndex}
    @DeleteMapping("/{matrixQuestionID}/columns/{columnIndex}")
    public ResponseEntity<Void> deleteColumn(@PathVariable Long matrixQuestionID, @PathVariable int columnIndex) {
        MatrixQuestion matrixQuestion = matrixQuestionService.getMatrixQuestionById(matrixQuestionID);
        matrixQuestion.getColumns().remove(columnIndex);
        matrixQuestionService.saveOrUpdateMatrixQuestion(matrixQuestion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Handles the GET request to retrieve all MatrixQuestion entities.
    // It uses the matrixQuestionService to fetch all MatrixQuestion entities from the database.
    // Returns a ResponseEntity containing the list of MatrixQuestion entities and an HTTP status of OK.
    @GetMapping
    public ResponseEntity<List<MatrixQuestion>> findAll() {
        List<MatrixQuestion> questions = matrixQuestionService.findAll();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

}