package com.drato.graduationthesis.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "exam_subject", schema = "public")
@IdClass(ExamSubjectId.class)
public class ExamSubject implements Serializable {
    @Id
    @Column(name = "exam_id")
    private long examId;

    @Id
    @Column(name = "subject_id")
    private long subjectId;

    @Column(name = "path")
    private String path;

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ExamSubject() {
    }

    public ExamSubject(long examId, long subjectId, String path) {
        this.examId = examId;
        this.subjectId = subjectId;
        this.path = path;
    }
}
