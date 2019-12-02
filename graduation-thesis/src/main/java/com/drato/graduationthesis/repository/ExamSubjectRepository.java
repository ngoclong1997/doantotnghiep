package com.drato.graduationthesis.repository;

import com.drato.graduationthesis.model.ExamSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamSubjectRepository extends JpaRepository<ExamSubject, Long> {
    ExamSubject getBySubjectIdAndExamId(long subjectId, long examId);
}
