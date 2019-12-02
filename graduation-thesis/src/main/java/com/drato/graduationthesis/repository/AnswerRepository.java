package com.drato.graduationthesis.repository;

import com.drato.graduationthesis.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    public List<Answer> findAllByExamId(Long examId);
    public void deleteAllByExamId(Long examId);
    List<Answer> findAllByExamIdAndSubjectId(Long examId, Long subjectId);
    void deleteAllByExamIdAndSubjectId(Long examId, Long subjectId);
}
