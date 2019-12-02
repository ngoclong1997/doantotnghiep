package com.drato.graduationthesis.repository;

import com.drato.graduationthesis.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface GradeRepository extends JpaRepository<Grade, Long> {
    public void deleteAllByExamId(Long examId);
    int countAllByStatusAndExamIdAndSubjectId(int status, long examId, long subjectId);
    int countAllByPointIsGreaterThanAndExamIdAndSubjectId(float point, long examId, long subjectId);
    @Query(value = "SELECT AVG(g.point) from Grade g where g.exam_id = ?1 and g.subject_id = ?2", nativeQuery = true)
    Float getAveragePointByExamIdAndSubjectId(long examId, long subjectId);
    List<Grade> getAllByExamIdAndSubjectId(long examId, long subjectId);
    @Query(value = "SELECT MAX(g.point) from Grade g where g.exam_id = ?1 and g.subject_id = ?2", nativeQuery = true)
    Float getMaxPointByExamIdAndSubjectId(long examId, long subjectId);
    @Query(value = "SELECT MIN(g.point) from Grade g where g.exam_id = ?1 and g.subject_id = ?2", nativeQuery = true)
    Float getMinPointByExamIdAndSubjectId(long examId, long subjectId);
}
