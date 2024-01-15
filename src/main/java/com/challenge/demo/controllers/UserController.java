package com.challenge.demo.controllers;

import com.challenge.demo.entities.MatrixQuestion;
import com.challenge.demo.entities.MatrixQuestionColumn;
import com.challenge.demo.entities.MatrixQuestionRow;
import com.challenge.demo.entities.Question;
import com.challenge.demo.entities.QuestionAnswer;
import com.challenge.demo.entities.Site;
import com.challenge.demo.entities.User;
import com.challenge.demo.entities.UserResponse;
import com.challenge.demo.entities.dtos.NextQuestionDTO;
import com.challenge.demo.entities.dtos.UserResponseDTO;
import com.challenge.demo.repositories.MatrixQuestionColumnRepository;
import com.challenge.demo.repositories.MatrixQuestionRepository;
import com.challenge.demo.repositories.MatrixQuestionRowRepository;
import com.challenge.demo.repositories.QuestionAnswerRepository;
import com.challenge.demo.repositories.QuestionRepository;
import com.challenge.demo.repositories.UserResponseRepository;
import com.challenge.demo.services.SiteService;
import com.challenge.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SiteService siteService;
    @Autowired
    UserResponseRepository userResponseRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    MatrixQuestionRepository matrixQuestionRepository;
    @Autowired
    QuestionAnswerRepository questionAnswerRepository;
    @Autowired
    MatrixQuestionRowRepository matrixQuestionRowRepository;
    @Autowired
    MatrixQuestionColumnRepository matrixQuestionColumnRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll(){
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        Optional<User> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/users/{uuid}")
    public ResponseEntity<User> findByUuid(@PathVariable UUID uuid){
        User user = userService.findByUuid(uuid);
        if(user != null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    //each post will create a new user with a random uuid
    @PostMapping("/sites/{siteId}/users")
    public ResponseEntity<User> add(@PathVariable Long siteId) {
        Optional<Site> site = siteService.findById(siteId);
        if(!site.isPresent())
            return ResponseEntity.notFound().build();

        UUID uuid = UUID.randomUUID();
        User user = userService.createUserWithUUID(uuid,site.get());
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        Optional<User> user = userService.findById(id);
        if(user.isPresent()){
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/users/uuid/{userUuid}")
    public ResponseEntity<String> deleteByUuid(@PathVariable UUID uuid){
        User user = userService.findByUuid(uuid);
        if(user != null){
            userService.deleteUser(user.getId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/next-question/{userId}")
    public ResponseEntity<NextQuestionDTO> nextQuestion(@PathVariable Long userId){
        Optional<User> user = userService.findById(userId);
        if(!user.isPresent())
            return ResponseEntity.notFound().build();
        Optional<NextQuestionDTO> nextQuestionDTO = userService.findNextQuestion(userId);
        return nextQuestionDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Answer to the given question
    @PostMapping("/answer-question")
    public ResponseEntity<UserResponse> answerQuestion(@RequestBody UserResponseDTO dto) {
        Optional<User> user = userService.findById(dto.getUserId());
        if(!user.isPresent())
            return ResponseEntity.notFound().build();
        if(dto.getQuestionId()==null && dto.getMatrixQuestionId()==null)
            return ResponseEntity.notFound().build();

        Optional<Question> question = questionRepository.findById(dto.getQuestionId());
        Optional<MatrixQuestion> matrixQuestion = matrixQuestionRepository.findById(dto.getMatrixQuestionId());

        Optional<QuestionAnswer> answer = questionAnswerRepository.findById(dto.getQuestionAnswerId());
        Optional<MatrixQuestionRow> row = matrixQuestionRowRepository.findById(dto.getMatrixQuestionRowId());
        Optional<MatrixQuestionColumn> column = matrixQuestionColumnRepository.findById(dto.getMatrixQuestionColumnId());

        if(!answer.isPresent() && !row.isPresent() && !column.isPresent())
            return ResponseEntity.notFound().build();

        if(answer.isPresent() && column.isPresent() || answer.isPresent() && row.isPresent())
            return ResponseEntity.notFound().build();

        if(!row.isPresent() && column.isPresent() || row.isPresent() && !column.isPresent())
            return ResponseEntity.notFound().build();

        UserResponse entity = new UserResponse();
        answer.ifPresent(entity::setQuestionAnswer);
        question.ifPresent(entity::setQuestion);
        entity.setUser(user.get());
        column.ifPresent(entity::setMatrixQuestionColumn);
        row.ifPresent(entity::setMatrixQuestionRow);
        matrixQuestion.ifPresent(entity::setMatrixQuestion);

        UserResponse response = userResponseRepository.save(entity);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
}
