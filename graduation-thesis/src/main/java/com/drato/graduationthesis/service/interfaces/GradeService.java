package com.drato.graduationthesis.service.interfaces;

import com.drato.graduationthesis.model.Grade;

import java.util.List;

public interface GradeService {
    public List<Grade> getAll();
    public Grade getById();
    public void deleteByExamId(Long examId);
    int countNumberOfValidAnswerSheetInExam(long examId, long subjectId);
    int countNumberOfStudentScoreBetterThanMedium(long examId, long subjectId);
    float calculateAvgScore(long examId, long subjectId);
    float getMaxPoint(long examId, long subjectId);
    List<Grade> getStudentSubjectScore(long examId, long subjectId);
    float getMinPoint(long examId, long subjectId);
}
