package com.drato.graduationthesis.model;

import java.io.Serializable;
import java.util.Objects;

public class ExamSubjectId implements Serializable {
    private Long examId;
    private Long subjectId;

    public ExamSubjectId() {
    }

    public ExamSubjectId(Long examId, Long subjectId) {
        this.examId = examId;
        this.subjectId = subjectId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamSubjectId examSubjectId = (ExamSubjectId) o;
        return examId.equals(examSubjectId.examId) &&
                subjectId.equals(examSubjectId.subjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examId, subjectId);
    }
}
