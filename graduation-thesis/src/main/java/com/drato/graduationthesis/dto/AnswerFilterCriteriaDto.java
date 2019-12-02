package com.drato.graduationthesis.dto;

import javax.validation.constraints.NotEmpty;

public class AnswerFilterCriteriaDto {
    @NotEmpty(message = "{constraints.not-empty}")
    String examId;
    @NotEmpty(message = "{constraints.not-empty}")
    String subjectId;

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
}
