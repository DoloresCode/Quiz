package com.challenge.demo.entities.dtos;

import com.challenge.demo.entities.UserResponse;

public class UserResponseDTO {
    private Long id;

    private Long userId;

    private Long questionId;

    private Long matrixQuestionId;
    private Long questionAnswerId;
    private Long matrixQuestionRowId;
    private Long matrixQuestionColumnId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getMatrixQuestionId() {
        return matrixQuestionId;
    }

    public void setMatrixQuestionId(Long matrixQuestionId) {
        this.matrixQuestionId = matrixQuestionId;
    }

    public Long getQuestionAnswerId() {
        return questionAnswerId;
    }

    public void setQuestionAnswerId(Long questionAnswerId) {
        this.questionAnswerId = questionAnswerId;
    }

    public Long getMatrixQuestionRowId() {
        return matrixQuestionRowId;
    }

    public void setMatrixQuestionRowId(Long matrixQuestionRowId) {
        this.matrixQuestionRowId = matrixQuestionRowId;
    }

    public Long getMatrixQuestionColumnId() {
        return matrixQuestionColumnId;
    }

    public void setMatrixQuestionColumnId(Long matrixQuestionColumnId) {
        this.matrixQuestionColumnId = matrixQuestionColumnId;
    }

    public static UserResponseDTO fromEntity(UserResponse userResponse) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(userResponse.getId());
        dto.setUserId(userResponse.getUser().getId());
        dto.setMatrixQuestionColumnId(userResponse.getMatrixQuestionColumn().getId());
        dto.setMatrixQuestionId(userResponse.getMatrixQuestion().getId());
        dto.setMatrixQuestionRowId(userResponse.getMatrixQuestionRow().getId());
        dto.setQuestionAnswerId(userResponse.getId());
        dto.setQuestionId(userResponse.getQuestion().getQuestionId());
        return dto;
    }

}
