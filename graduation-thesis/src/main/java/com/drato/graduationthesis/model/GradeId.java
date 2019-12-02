package com.drato.graduationthesis.model;

import java.io.Serializable;
import java.util.Objects;

public class GradeId implements Serializable {
    private Long studentId;
    private Long subjectId;

    public GradeId() {
    }

    public GradeId(Long studentId, Long subjectId) {
        this.studentId = studentId;
        this.subjectId = subjectId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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
        GradeId gradeId = (GradeId) o;
        return studentId.equals(gradeId.studentId) &&
                subjectId.equals(gradeId.subjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, subjectId);
    }
}
