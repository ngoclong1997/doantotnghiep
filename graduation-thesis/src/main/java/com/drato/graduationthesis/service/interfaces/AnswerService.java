package com.drato.graduationthesis.service.interfaces;

import com.drato.graduationthesis.model.Answer;

import java.util.List;

public interface AnswerService {
    public List<Answer> getAll();

    public Answer getById(Long id);

    public List<Answer> getAllByExamId(Long examId);

    public List<Answer> getAllByExamAndSubject(Long examId, Long subjectId);

    public void deleteByExamId(Long examId);

    public void insertAnswers(List<Answer> answers);

}
