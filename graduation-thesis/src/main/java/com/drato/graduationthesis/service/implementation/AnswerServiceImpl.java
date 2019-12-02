package com.drato.graduationthesis.service.implementation;

import com.drato.graduationthesis.model.Answer;
import com.drato.graduationthesis.repository.AnswerRepository;
import com.drato.graduationthesis.service.interfaces.AnswerService;
import com.drato.graduationthesis.service.interfaces.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerRepository repository;
    @Autowired
    SecurityService securityService;

    @Override
    public List<Answer> getAll() {
        return repository.findAll();
    }

    @Override
    public Answer getById(Long id) {
        Optional<Answer> optionalUser = repository.findById(id);
        return optionalUser.orElse(null);
    }

    @Override
    public List<Answer> getAllByExamId(Long examId) {
        return repository.findAllByExamId(examId);
    }

    @Override
    public List<Answer> getAllByExamAndSubject(Long examId, Long subjectId) {
        return repository.findAllByExamIdAndSubjectId(examId, subjectId);
    }

    @Override
    public void deleteByExamId(Long examId) {
        repository.deleteAllByExamId(examId);
    }

    @Override
    public void insertAnswers(List<Answer> answers) {
        String username = securityService.getCurrentUsername();
        List<Answer> ans = getAllByExamAndSubject(answers.get(0).getExamId(), answers.get(0).getSubjectId());
        if (ans.size() == 0) {
            for (Answer e : answers) {
                Date now = Calendar.getInstance().getTime();
                e.setCreatedDate(now);
                e.setModifiedDate(now);
                e.setAddBy(username);
                e.setModifiedBy(username);
            }
            repository.saveAll(answers);
        } else {
            repository.deleteAllByExamIdAndSubjectId(answers.get(0).getExamId(), answers.get(0).getSubjectId());
            repository.saveAll(answers);
            for (Answer e : answers) {
                Date now = Calendar.getInstance().getTime();
                e.setCreatedDate(ans.get(0).getCreatedDate());
                e.setModifiedDate(now);
                e.setAddBy(ans.get(0).getAddBy());
                e.setModifiedBy(username);
            }
        }
    }
}
