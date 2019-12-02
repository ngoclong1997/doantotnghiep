package com.drato.graduationthesis.service.implementation;

import com.drato.graduationthesis.model.ExamSubject;
import com.drato.graduationthesis.repository.ExamSubjectRepository;
import com.drato.graduationthesis.service.interfaces.ExamSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamSubjectServiceImpl implements ExamSubjectService {

    @Autowired
    ExamSubjectRepository repository;

    @Override
    public void updatePath(String path, long examId, long subjectId) {
        ExamSubject examSubject = repository.getBySubjectIdAndExamId(subjectId, examId);
        examSubject.setPath(path);
        repository.save(examSubject);
    }

    @Override
    public String getPath(long examId, long subjectId) {
        return repository.getBySubjectIdAndExamId(subjectId, examId).getPath();
    }
}