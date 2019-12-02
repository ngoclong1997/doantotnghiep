package com.drato.graduationthesis.service.interfaces;

import com.drato.graduationthesis.dto.ExamDto;
import com.drato.graduationthesis.model.Exam;
import com.drato.graduationthesis.model.Subject;

import java.text.ParseException;
import java.util.List;

public interface ExamService {
    public void createExam(ExamDto exam) throws ParseException;
    public List<Exam> getAll();
    public Exam getById(Long id);
    public List<Subject> getExamSubjects(Long id);
    void editExam(ExamDto exam) throws ParseException;
    void deleteExam(Exam exam);
    public ExamDto convertExamToExamDto(Exam exam);
    public Exam convertExamDtoToExam(ExamDto examDto) throws ParseException;
}
