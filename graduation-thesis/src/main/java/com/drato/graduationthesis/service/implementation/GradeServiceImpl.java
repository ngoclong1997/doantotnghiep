package com.drato.graduationthesis.service.implementation;

import com.drato.graduationthesis.model.Grade;
import com.drato.graduationthesis.repository.GradeRepository;
import com.drato.graduationthesis.service.interfaces.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    GradeRepository repository;

    @Override
    public List<Grade> getAll() {
        return null;
    }

    @Override
    public Grade getById() {
        return null;
    }

    @Override
    public void deleteByExamId(Long examId) {
        repository.deleteAllByExamId(examId);
    }

    @Override
    public int countNumberOfValidAnswerSheetInExam(long examId, long subjectId) {
        return repository.countAllByStatusAndExamIdAndSubjectId(Grade.ANSWER_SHEET_VALID, examId, subjectId);
    }

    @Override
    public int countNumberOfStudentScoreBetterThanMedium(long examId, long subjectId) {
        return repository.countAllByPointIsGreaterThanAndExamIdAndSubjectId(5f, examId, subjectId);
    }

    @Override
    public float calculateAvgScore(long examId, long subjectId) {
        Float result = repository.getAveragePointByExamIdAndSubjectId(examId, subjectId);
        if (result == null) {
            return 0;
        }
        return result;
    }

    @Override
    public float getMaxPoint(long examId, long subjectId) {
        Float result =  repository.getMaxPointByExamIdAndSubjectId(examId, subjectId);
        if (result == null) {
            return 0;
        }
        return result;
    }

    @Override
    public float getMinPoint(long examId, long subjectId) {
        Float result =  repository.getMinPointByExamIdAndSubjectId(examId, subjectId);
        if (result == null) {
            return 0;
        }
        return result;
    }

    @Override
    public List<Grade> getStudentSubjectScore(long examId, long subjectId) {
        return repository.getAllByExamIdAndSubjectId(examId, subjectId);
    }
}
